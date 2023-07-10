package com.m.jcp.chapter_11.thread_local;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * @author zhangtian1
 * 为每个线程提供独立的上下文
 */
@Slf4j
public record CustomerOrder(int customerId) implements Runnable {
    private static final Random rnd = new Random();

    private static final ThreadLocal<Order>
            customerOrder = new ThreadLocal<>();

    @Override
    public void run() {
        log.info(">>> Given customerId:{} | order:{} | thread:{}", customerId, customerOrder.get(), Thread.currentThread().getName());
        customerOrder.set(new Order(customerId));
        try {
            Thread.sleep(rnd.nextInt(2000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("<<< exception:{}", e.getMessage());
        }
        log.info(">>> Given customerId:{} | order:{} | thread:{}", customerId, customerOrder.get(), Thread.currentThread().getName());
        customerOrder.remove();
    }

    public static void main(String[] args) {
        CustomerOrder co1 = new CustomerOrder(1);
        CustomerOrder co2 = new CustomerOrder(2);
        CustomerOrder co3 = new CustomerOrder(3);
        new Thread(co1).start();
        new Thread(co2).start();
        new Thread(co3).start();
    }
}
