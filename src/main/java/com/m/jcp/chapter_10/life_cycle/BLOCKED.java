package com.m.jcp.chapter_10.life_cycle;

/**
 * @author zhangtian1
 */
public class BLOCKED {
    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(BLOCKED::syncMethod);
        Thread t2 = new Thread(BLOCKED::syncMethod);

        t1.start();
        Thread.sleep(2000);
        t2.start();

        // t1 state: RUNNABLE
        System.out.println("t1 state: " + t1.getState());
        
        // t2 state: BLOCKED
        System.out.println("t2 state: " + t2.getState());
    }

    public static synchronized void syncMethod() {
        System.out.println("Thread " + Thread.currentThread().getName() + " is running syncMethod");
        while (true) {
            // do nothing
        }
    }
}
