package com.m.jcp.chapter_10.pool.single_thread;

import lombok.extern.slf4j.Slf4j;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * @author zhangtian1
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        AssemblyLine.start(new ConcurrentLinkedDeque<>());
        Thread.sleep(10 * 1000);
        Queue<String> remainingTasks = AssemblyLine.stop();

        Thread.sleep(2000);

        System.out.println(">>> starting again");
        AssemblyLine.start(new ConcurrentLinkedDeque<>(remainingTasks));
        Thread.sleep(6000);
        Queue<String> lastBatch = AssemblyLine.stop();
        log.info(">>> result:{}", lastBatch.size());
    }
}
