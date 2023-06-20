package com.m.jcp.chapter_10.life_cycle;

/**
 * @author zhangtian1
 */
public class RUNNABLE {
    public static void main(String[] args) {
        //@formatter:off
        Thread t = new Thread(() -> {});
        //@formatter:on
        t.start();

        // thread state: RUNNABLE
        System.out.println("thread state: " + t.getState());
    }
}
