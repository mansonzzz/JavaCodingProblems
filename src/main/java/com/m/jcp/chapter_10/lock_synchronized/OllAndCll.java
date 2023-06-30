package com.m.jcp.chapter_10.lock_synchronized;

/**
 * @author zhangtian1
 * Object level lock and Class level lock
 * 获取的是两把锁，互不影响
 */
public class OllAndCll {

    public synchronized void nonStaticMethod() {
        System.out.println(">>> nonStaticMethod():" + Thread.currentThread().getName());
        //@formatter:off
        while (true) {}
        //@formatter:on
    }

    public synchronized static void staticMethod() {
        System.out.println(">>> staticMethod():" + Thread.currentThread().getName());
        //@formatter:off
        while (true) {}
        //@formatter:on.
    }

    public static void main(String[] args) {
        OllAndCll instance1 = new OllAndCll();
        // 对象锁 可以获取到锁
        new Thread(instance1::nonStaticMethod, "t1").start();
        // 类锁 可以获取到锁
        new Thread(() -> instance1.staticMethod(), "t2").start();
    }

}
