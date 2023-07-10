package com.m.jcp.chapter_11.thread_local;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author zhangtian1
 * 为每个线程提供独立的实例
 */
@Slf4j
public class ThreadSafeStringBuilder implements Runnable {

    private static final ThreadLocal<StringBuilder> threadLocal = ThreadLocal.withInitial(StringBuilder::new);
    private static final Random rnd = new Random();

    public static void main(String[] args) {
        ThreadSafeStringBuilder threadSafe = new ThreadSafeStringBuilder();

        for (int i = 0; i < 3; i++) {
            new Thread(threadSafe, "thread-" + i).start();
        }
    }

    @Override
    public void run() {
        log.info(">>> thread:{}, threadLocal:{}", Thread.currentThread().getName(), threadLocal.get());
        try {
            Thread.sleep(rnd.nextInt(2000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        threadLocal.get().append(Thread.currentThread().getName());
        log.info(">>> thread:{}, threadLocal:{}", Thread.currentThread().getName(), threadLocal.get());
        threadLocal.set(null);
        log.info(">>> thread:{}, threadLocal:{}", Thread.currentThread().getName(), threadLocal.get());
    }
}
