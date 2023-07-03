package com.m.jcp.chapter_10.pool.fixed_thread;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author zhangtian1
 */
public interface Constants {
    int MAX_PROD_TIME_MS = 1000;
    int MAX_CONS_TIME_MS = 3 * 1000;
    int TIMEOUT_MS = MAX_PROD_TIME_MS + MAX_CONS_TIME_MS + 1000;

    Random rnd = new Random();
    Queue<String> queue = new ConcurrentLinkedQueue<>();

    int PRODUCER_NUM = 3;
    int CONSUMER_NUM = 2;
}
