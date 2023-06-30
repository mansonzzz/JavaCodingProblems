package com.m.jcp.chapter_10.lock_synchronized;

/**
 * @author zhangtian1
 */
public class TwoCll {
    public synchronized static void staticMethod1() {
        System.out.println("staticMethod1(): " + Thread.currentThread().getName());

        while (true) {
        }
    }

    public synchronized static void staticMethod2() {
        System.out.println("staticMethod2(): " + Thread.currentThread().getName());
        //@formatter:off
        while (true) {}
        //@formatter:on
    }

    public static void main(String[] args) {
        TwoCll instance1 = new TwoCll();
        TwoCll instance2 = new TwoCll();

        new Thread(() -> instance1.staticMethod1()).start();

        new Thread(() -> instance2.staticMethod2()).start();
    }
}
