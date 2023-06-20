package com.m.jcp.chapter_10.life_cycle;

/**
 * @author zhangtian1
 */
public class TERMINATED {
    public static void main(String[] args) throws Exception {
        //@formatter:off
        Thread t = new Thread(()->{});
        //@formatter:on
        t.start();
        // 保证主线程打印t state之前，t线程已经执行结束
        Thread.sleep(1000);
        // t state: TERMINATED
        System.out.println("t state: " + t.getState());
    }
}
