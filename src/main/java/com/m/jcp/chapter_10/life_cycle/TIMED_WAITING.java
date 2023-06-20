package com.m.jcp.chapter_10.life_cycle;

/**
 * @author zhangtian1
 */
public class TIMED_WAITING {
    public static void main(String[] args) throws Exception {
        Thread t = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t.start();
        // 保证主线程打印t state之前，t线程已经启动
        Thread.sleep(500);
        // t state: TIMED_WAITING
        System.out.println("t state: " + t.getState());
    }
}
