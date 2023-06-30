package com.m.jcp.chapter_10.lock_synchronized;

/**
 * @author zhangtian1
 */
public class ClazzOll3 {

    private final Object LOCK = new Object();

    public void test() {
        synchronized (LOCK) {
            System.out.println(">>> Thread: " + Thread.currentThread().getName() + " is running");
            //@formatter:off
            while(true){}
            //@formatter:on
        }
    }

    public static void main(String[] args) {
        ClazzOll3 instance = new ClazzOll3();
        new Thread(instance::test, "t1").start();
        new Thread(instance::test, "t2").start();
    }
}
