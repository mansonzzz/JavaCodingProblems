package com.m.jcp.chapter_10.lock_synchronized;

/**
 * @author zhangtian1
 * Class level lock
 */
public class ClazzCll1 {
    public synchronized static void methodCll() {
        System.out.println(">>> methodCll():" + Thread.currentThread().getName());
        //@formatter:off
        while (true) {}
        //@formatter:on.
    }

    public static void main(String[] args) {
        new Thread(ClazzCll1::methodCll, "t1").start();
        new Thread(ClazzCll1::methodCll, "t2").start();
    }
}
