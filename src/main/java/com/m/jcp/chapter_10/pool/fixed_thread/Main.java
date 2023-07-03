package com.m.jcp.chapter_10.pool.fixed_thread;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author zhangtian1
 */
public class Main {
    public static void main(String[] args) throws Exception {
        AssemblyLine.start(new ConcurrentLinkedDeque<>());
        Thread.sleep(2000);
        Queue<String> remains = AssemblyLine.stop();

        Thread.sleep(2000);
        AssemblyLine.start(new ConcurrentLinkedDeque<>(remains));
        Thread.sleep(2000);
        AssemblyLine.stop();
    }
}
