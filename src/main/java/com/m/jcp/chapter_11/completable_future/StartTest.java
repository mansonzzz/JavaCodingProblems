package com.m.jcp.chapter_11.completable_future;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangtian1
 */
@Slf4j
public class StartTest {

    private volatile boolean serviceAvailable;

    public void setServiceAvailable(boolean serviceAvailable) {
        this.serviceAvailable = serviceAvailable;
    }

    public void service() {
        new Thread(() -> {
            log.info(">>> Waiting for service to be available...");
            while (!serviceAvailable) {
                // 这个方法本身并不执行任何操作，它只是一个提示，用于告诉 JVM 当前线程正在旋转等待
                Thread.onSpinWait();
            }
            log.info(">>> Service is available now.");
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        var startTest = new StartTest();
        startTest.service();
        Thread.sleep(5000);
        startTest.setServiceAvailable(true);
    }
}
