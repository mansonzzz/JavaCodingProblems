package com.m.jcp.chapter_10.life_cycle;

/**
 * @author zhangtian1
 */
public class WAITING {
    public static void main(String[] args) {
        new Thread(() -> {
            Thread t1 = Thread.currentThread();
            Thread t2 = new Thread(() -> {
                try {
                    Thread.sleep(2000);
                    // t1 state: WAITING
                    System.out.println("t1 state: " + t1.getState());
                    // t2 state: RUNNABLE
                    System.out.println("t2 state: " + Thread.currentThread().getState());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            t2.start();
            try {
                // 使当前线程等待t2线程终止
                t2.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
