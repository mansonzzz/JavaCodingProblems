package com.m.jcp.chapter_11.interruptible_methods;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * @author zhangtian1
 * <p>
 * 在Java中，当一个线程处于阻塞状态（例如，调用了Object.wait(), Thread.sleep(), Thread.join()等方法），并且其他线程调用了这个线程的interrupt()方法试图中断它时，阻塞方法会抛出InterruptedException，并且清除线程的中断状态。
 * </p>
 * <p>
 * 这样设计的原因是，InterruptedException是一种受检异常，它表示线程被其他线程中断了。
 * 当这个异常被抛出时，它就表示线程已经响应了中断请求，并且已经从阻塞状态中退出了。因此，没有必要再保留中断状态了。
 * 这种设计也使得开发者可以更灵活地处理中断。
 * </p>
 * 当捕获到InterruptedException后，开发者可以选择忽略它，
 * 或者重新设置中断状态（通过调用Thread.currentThread().interrupt()），或者抛出一个新的未检查异常，或者以其他方式表示线程应该停止执行。
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
        TransferQueue<String> queue = new LinkedTransferQueue<>();
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    log.info(">>> For 3s the thread " + Thread.currentThread().getName() + " will try to poll an element from the queue");
                    queue.poll(3000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    log.error("<<< The thread {} was interrupted? {}", Thread.currentThread().getName(), Thread.currentThread().isInterrupted());
                    // reset the interrupted status
                    Thread.currentThread().interrupt();
                    log.error("<<< The thread {} was interrupted? {}", Thread.currentThread().getName(), Thread.currentThread().isInterrupted());
                }
            }
            log.info(">>> The execution was stopped");
        });
        thread.start();
        Thread.sleep(1500);
        thread.interrupt();
    }
}
