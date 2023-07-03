package com.m.jcp.chapter_10.pool.single_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangtian1
 * producer doesn't wait for the consumer to be available
 */
@Slf4j
public class AssemblyLine implements Constants {
    private static volatile boolean runningProducer;
    private static volatile boolean runningConsumer;

    private static ExecutorService producerService;
    private static ExecutorService consumerService;
    private static final Producer producer = new Producer();
    private static final Consumer consumer = new Consumer();

    private AssemblyLine() {
        throw new AssertionError("There is no AssemblyLine instance for you!");
    }

    private static class Producer implements Runnable {
        @Override
        public void run() {
            while (runningProducer) {
                try {
                    String bulb = "bulb-" + rnd.nextInt(1000);
                    Thread.sleep(rnd.nextInt(MAX_PROD_TIME_MS));
                    queue.offer(bulb);
                    log.info(">>> checked:{}", bulb);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    log.error("<<< producer error:{}", e.getMessage(), e);
                    break;
                }
            }
        }
    }

    private static class Consumer implements Runnable {
        @Override
        public void run() {
            while (runningConsumer) {
                try {
                    String bulb = queue.poll();
                    if (bulb != null) {
                        Thread.sleep(rnd.nextInt(MAX_CONS_TIME_MS));
                        log.info(">>> packed:{}", bulb);
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("<<< consumer error:{}", e.getMessage(), e);
                    break;
                }
            }
        }
    }

    public static void start(Queue<String> remainingTasks) {
        if (runningProducer || runningConsumer) {
            log.info(">>> skip...");
            return;
        }
        log.info(">>> starting");
        log.info(">>> remaining tasks:{}", remainingTasks.size());

        runningProducer = true;
        producerService = Executors.newSingleThreadExecutor();
        producerService.execute(producer);

        runningConsumer = true;
        consumerService = Executors.newSingleThreadExecutor();
        consumerService.execute(consumer);
    }

    public static Queue<String> stop() {
        log.info(">>> stopping");
        boolean isProducedown = shutdownProducer();
        boolean isConsumedown = shutdownConsumer();

        if (!isProducedown || !isConsumedown) {
            log.error("<<< stop error");
            System.exit(0);
        }
        log.info(">>> successfully stopped");
        return queue;
    }

    private static boolean shutdownProducer() {
        runningProducer = false;
        return shutDownExecutor(producerService);
    }

    private static boolean shutdownConsumer() {
        runningConsumer = false;
        return shutDownExecutor(consumerService);
    }

    private static boolean shutDownExecutor(ExecutorService executor) {
        executor.shutdown();
        try {
            // 等待一段时间，如果还没有结束，就强制结束
            if (!executor.awaitTermination(TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
                return executor.awaitTermination(TIMEOUT_MS, TimeUnit.MILLISECONDS);
            }
            return true;
        } catch (Exception e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            log.error("<<< shudown error:{}", e.getMessage(), e);
        }
        return false;
    }
}
