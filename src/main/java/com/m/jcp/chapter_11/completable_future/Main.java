package com.m.jcp.chapter_11.completable_future;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangtian1
 * add `-ea` to VM options to enable assertion
 */
@Slf4j
public class Main {
    public static void main(String[] args) throws Exception {
//        CustomerAsyncs.printOrder();
//        CustomerAsyncs.fetchOrderSummary();
//        CustomerAsyncs.fetchOrderSummaryExecutor();
//        CustomerAsyncs.fetchInvoiceTotalSign();
//        CustomerAsyncs.fetchAndPrintOrder();
//        CustomerAsyncs.fetchNonChainCallUseThenApplyAsync();
//        CustomerAsyncs.deliverOrderNotifyCustomer();
//        CustomerAsyncs.fetchV1();
//        CustomerAsyncs.fetchV2();
//        CustomerAsyncs.fetchV3();
//        CustomerAsyncs.fetchOrderTotalException();
//        CustomerAsyncs.fetchInvoiceTotalSignChainOfException();
//        CustomerAsyncs.fetchOrderTotalExceptionAsync();
//        CustomerAsyncs.printInvoiceException();
//        CustomerAsyncs.fetchTotalHandle();
//        testForUniApply();
        testForPostComplete();
    }

    public static void testForUniApply() throws InterruptedException, ExecutionException {
        CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "1";
        });
        CompletableFuture<String> task2 = task1.thenApply(s -> s + "2");
        task2.get();
    }

    public static void test01() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> task1 = CompletableFuture
                // step1
                .supplyAsync(() -> 1);
        CompletableFuture<Integer> task2 = task1// step2
                .thenApply(i -> i + 1);
        task1// step3
                .thenApply(i -> i + 1)
                .get();


    }

    /**
     * 深度优先遍历
     */
    public static void testForPostComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> base = new CompletableFuture<>();
        CompletableFuture<String> future = base.thenApply(s -> {
            log.info(">>> Thread:{},Result:{}", Thread.currentThread().getName(), "2");
            return s + " 2";
        });
        // 使用 thenAccept 时，平级任务也是顺序执行的，每出栈一个任务就会顺序执行这个任务持有的任务栈
        // 使用 thenApplyAsync 时，平级任务是并行执行的
        // base.thenAcceptAsync(s -> log.info(">>> Thread:{},Result:{}", Thread.currentThread().getName(), s + "3-1")).thenAccept(aVoid -> log.info(">>> Thread:{},Result:{}", Thread.currentThread().getName(), "3-2"));
        base.thenAccept(s -> log.info(">>> Thread:{},Result:{}", Thread.currentThread().getName(), s + "3-1")).thenAccept(aVoid -> log.info(">>> Thread:{},Result:{}", Thread.currentThread().getName(), "3-2"));
        base.thenAccept(s -> log.info(">>> Thread:{},Result:{}", Thread.currentThread().getName(), s + "4-1")).thenAccept(aVoid -> log.info(">>> Thread:{},Result:{}", Thread.currentThread().getName(), "4-2"));
        base.complete("1");
        log.info("base result: {}", base.get());
        log.info("future result: {}", future.get());
    }
}
