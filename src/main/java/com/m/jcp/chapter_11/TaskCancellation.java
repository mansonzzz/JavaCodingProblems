package com.m.jcp.chapter_11;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhangtian1
 */
public class TaskCancellation implements Runnable {

    private volatile boolean cancelled;
    private final List<Integer> randoms = new CopyOnWriteArrayList<>();
    private final Random rnd = new Random();

    @Override
    public void run() {
        while (!cancelled) {
            randoms.add(rnd.nextInt(100));
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public List<Integer> getRandoms() {
        return randoms;
    }

    public static void main(String[] args) throws InterruptedException {
        var task = new TaskCancellation();
        ExecutorService executor = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 100; i++) {
            executor.execute(task);
        }

        Thread.sleep(100);

        task.cancel();
        executor.shutdownNow();
        System.out.println(task.getRandoms().size());
    }
}
