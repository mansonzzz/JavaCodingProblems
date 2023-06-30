package com.m.jcp.chapter_10.lock_synchronized;

/**
 * @author zhangtian1
 * Class level lock
 */
public class ClazzCll3 {

    private static final Object LOCK = new Object();

    public void method() {
        System.out.println(">>> Thread: " + Thread.currentThread().getName() + " is in method");
        synchronized (LOCK) {
            System.out.println(">>> Thread: " + Thread.currentThread().getName() + " is running");
            //@formatter:off
            while (true) {}
            //@formatter:on
        }
    }

    public static void main(String[] args) {
        // 两个线程都会进入 synchronized 代码块，但是只有一个线程能够执行 while 循环
        new Thread(() -> new ClazzCll3().method(), "t1").start();
        new Thread(() -> new ClazzCll3().method(), "t2").start();
    }
}
