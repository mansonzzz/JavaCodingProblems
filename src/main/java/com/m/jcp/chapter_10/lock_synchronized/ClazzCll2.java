package com.m.jcp.chapter_10.lock_synchronized;

/**
 * @author zhangtian1
 * Class level lock
 */
public class ClazzCll2 {
    public void method() {
        synchronized (ClazzCll2.class) {
            System.out.println(">>> method():" + Thread.currentThread().getName());
            //@formatter:off
            while (true) {}
            //@formatter:on.
        }
    }

    public static void main(String[] args) {
        // 只有 t1 可以获取到锁
        new Thread(new ClazzCll2()::method, "t1").start();
        new Thread(new ClazzCll2()::method, "t2").start();
    }
}
