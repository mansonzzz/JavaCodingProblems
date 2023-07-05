package com.m.jcp.chapter_11.completable_future;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.UUID;
import java.util.concurrent.*;

/**
 * @author zhangtian1
 */
@Slf4j
public class CustomerAsyncs {
    public static void printOrder() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> cfPrintOrder = CompletableFuture.runAsync(() -> {
            try {
                log.info(">>> Order is printed by:{}", Thread.currentThread().getName());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                log.error("<<< printOrder error:{}", e.getMessage(), e);
            }
        });
        cfPrintOrder.get(); // block until the task is done
        log.info(">>> Done!");
    }

    public static void fetchOrderSummary() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cfOrderSummary = CompletableFuture.supplyAsync(() -> {
            log.info(">>> Order summary is fetched by:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return UUID.randomUUID().toString();
        });
        String summary = cfOrderSummary.get();
        log.info(">>> summary:{}", summary);
    }

    public static void fetchOrderSummaryExecutor() throws ExecutionException, InterruptedException {
        ThreadFactory basicThreadFactory = new BasicThreadFactory.Builder()
                .namingPattern("Customer-Asyncs-%d").build();
        ExecutorService executor = Executors.newSingleThreadExecutor(basicThreadFactory);
        CompletableFuture<String> cfOrderSummary = CompletableFuture.supplyAsync(() -> {
            log.info(">>> Order summary is fetched by:{}", Thread.currentThread().getName());
            assert Thread.currentThread().getName().startsWith("Customer-Asyncs-");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "summary-1";
        }, executor);
        String summary = cfOrderSummary.get();
        assert summary.equals("summary-1");
        executor.shutdownNow();
    }

    public static void fetchInvoiceTotalSign() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cfOrderSummary = CompletableFuture.supplyAsync(() -> {
                    log.info(">>> Order summary is fetched by:{}", Thread.currentThread().getName());
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return "summary-1";
                })
                .thenApply(s -> s + " Total: $123")
                .thenApply(s -> s + " Signed");
        String result = cfOrderSummary.get();
        assert result.equals("summary-1 Total: $123 Signed");
    }

    public static void fetchAndPrintOrder() {
        CompletableFuture<String> cfOrderSummary = CompletableFuture.supplyAsync(() -> {
            log.info(">>> Fetch order by:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "summary-1";
        });
    }
}
