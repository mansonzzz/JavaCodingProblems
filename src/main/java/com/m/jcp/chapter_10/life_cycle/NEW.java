package com.m.jcp.chapter_10.life_cycle;

/**
 * @author zhangtian1
 */
public class NEW {
    public static void main(String[] args) {
        var n = new NEW();
        // thread state: NEW
        n.newThread();
    }

    public void newThread() {
        Thread thread = new Thread(() -> {
            System.out.println("hello world");
        });
        System.out.println("thread state: " + thread.getState());
    }
}
