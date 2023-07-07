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
    public static void testForPostComplete() {
        CompletableFuture<String> task1 = new CompletableFuture<>();
        CompletableFuture<String> task2 = task1.thenApply(t -> {
            System.out.println("2");
            return "2";
        });
        task2.thenAccept(t -> System.out.println("2.1"));
        task2.thenAccept(t -> System.out.println("2.2"))
                .thenAccept(t -> System.out.println("2.2.1"))
                .thenAccept(t -> System.out.println("2.2.1.1"));

        CompletableFuture<String> task3 = task1.thenApply(t -> {
            System.out.println("3");
            return "3";
        });
        task3.thenAccept(t -> System.out.println("3.1"));
        task3.thenAccept(t -> System.out.println("3.2"))
                .thenAccept(t -> System.out.println("3.2.1"))
                .thenAccept(t -> System.out.println("3.2.1.1"));

        CompletableFuture<String> task4 = task1.thenApply(t -> {
            System.out.println("4");
            return "4";
        });

        task1.complete("1");
    }
}
