package com.m.jcp.chapter_10.lock_synchronized;

/**
 * @author zhangtian1
 * Obejct level lock
 */
public class ClazzOll1 {

    public synchronized void test() {
        System.out.println(">>> Thread: " + Thread.currentThread().getName() + " is running");
        //@formatter:off
        while(true){}
        //@formatter:on
    }

    public static void main(String[] args) {
        ClazzOll1 instance = new ClazzOll1();
        // 只有 t1 能够执行，t2 一直在等待
        new Thread(instance::test, "t1").start();
        new Thread(instance::test, "t2").start();
    }

}
