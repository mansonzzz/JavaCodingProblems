package com.m.jcp.chapter_10.pool.fixed_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangtian1
 */
@Slf4j
public class AssemblyLine implements Constants {
    private static final Producer producer = new Producer();
    private static final Consumer consumer = new Consumer();

    private static ExecutorService producerService;
    private static ExecutorService consumerService;

    private static volatile boolean runningProducer;
    private static volatile boolean runningConsumer;

    public static void start(Queue<String> remainingTasks) {
        if (runningProducer || runningConsumer) {
            log.info(">>> skip...");
            return;
        }
        log.info(">>> starting");
        log.info(">>> remaining tasks:{}", remainingTasks.size());
        runningProducer = true;
        producerService = Executors.newFixedThreadPool(PRODUCER_NUM);
        runningConsumer = true;
        consumerService = Executors.newFixedThreadPool(CONSUMER_NUM);

        for (int i = 0; i < PRODUCER_NUM; i++) {
            producerService.execute(producer);
        }
        for (int i = 0; i < CONSUMER_NUM; i++) {
            consumerService.execute(consumer);
        }
    }

    public static Queue<String> stop() {
        log.info(">>> stopping");
        boolean isProduceDown = shutdownProducer();
        boolean isConsumeDown = shutdownConsumer();

        if (!isProduceDown || !isConsumeDown) {
            log.error(">>> stop error");
            System.exit(0);
        }
        log.info(">>> successfully stopped remains:{}", queue.size());
        return queue;
    }

    private static boolean shutdownProducer() {
        runningProducer = false;
        return shutdownExecutor(producerService);
    }

    private static boolean shutdownConsumer() {
        runningConsumer = false;
        return shutdownExecutor(consumerService);
    }

    private static boolean shutdownExecutor(ExecutorService executor) {
        try {
            executor.shutdown();
            if (!executor.awaitTermination(TIMEOUT_MS, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
                return executor.awaitTermination(TIMEOUT_MS, TimeUnit.MILLISECONDS);
            }
            return true;
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
            log.error(">>> shutdown error:{}", e.getMessage(), e);
        }
        return false;
    }

    private static class Producer implements Runnable {
        @Override
        public void run() {
            while (runningProducer) {
                try {
                    String bulb = "bulb-" + rnd.nextInt(1000);
                    Thread.sleep(MAX_PROD_TIME_MS);
                    queue.offer(bulb);
                    log.info(">>> {} - checked:{}", Thread.currentThread().getName(), bulb);
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
                    Thread.sleep(MAX_CONS_TIME_MS);
                    log.info(">>> {} - packed:{}", Thread.currentThread().getName(), bulb);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    log.error("<<< consumer error:{}", e.getMessage(), e);
                    break;
                }
            }
        }
    }
}
