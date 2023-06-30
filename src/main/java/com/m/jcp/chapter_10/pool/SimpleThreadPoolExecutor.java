package com.m.jcp.chapter_10.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Thread.sleep;

/**
 * @author zhangtian1
 */
public class SimpleThreadPoolExecutor implements Runnable {

    private final int taskId;

    public SimpleThreadPoolExecutor(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public void run() {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(">>> Execute task: " + taskId + " via thread: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws Exception {
        var queue = new LinkedBlockingQueue<Runnable>(5);
        final var counter = new AtomicInteger();

        ThreadFactory factory = (Runnable r) -> {
            System.out.println(">>> Create thread: " + counter.incrementAndGet());
            return new Thread(r, "thread-" + counter.get());
        };

        RejectedExecutionHandler rejectedHandler = (Runnable r, ThreadPoolExecutor executor) -> {
            if (r instanceof SimpleThreadPoolExecutor task) {
                System.out.println(">>> Reject task: " + task.taskId);
            }
        };

        var threadPoolExecutor = new ThreadPoolExecutor(
                // corePoolSize: 线程池中核心线程的数量，会一直存活，即使没有任务需要执行
                10,
                // maximumPoolSize: 线程池中最大线程数量，当缓冲队列满了之后，如果还有任务进来，就会创建新的线程执行任务
                20,
                // keepAliveTime: 当这个时间过后，闲置的线程将从池中移除（这些是超过corePoolSize的闲置线程）
                1, TimeUnit.SECONDS,
                // workQueue: 缓冲队列，用于存放等待执行的任务
                queue,
                // threadFactory: 线程工厂，用于创建线程，一般用默认的即可
                factory,
                // handler: 拒绝策略，当队列和线程池都满了之后，如何拒绝新任务的策略
                rejectedHandler);

        for (int i = 0; i < 50; i++) {
            threadPoolExecutor.execute(new SimpleThreadPoolExecutor(i));
        }
        threadPoolExecutor.shutdown();
        // 等待线程池终止
        boolean awaitTermination = threadPoolExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
        System.out.println(">>> awaitTermination: " + awaitTermination);
    }
}
