package com.m.jcp.chapter_11.completable_future;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author zhangtian1
 */
@Slf4j
public class CustomerAsyncsV2 {
    private static CompletableFuture<String> fetchOrder(String customerId) {
        return CompletableFuture.supplyAsync(() -> "Order of " + customerId);
    }

    private static CompletableFuture<Integer> computeTotal(String order) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Computing total for order: " + order);
            return new Random().nextInt(1000);
        });
    }

    private static CompletableFuture<String> packProducts(String order) {
        return CompletableFuture.supplyAsync(() -> {
            return "Order: " + order
                    + " | Product 1, Product 2, Product 3, ... ";
        });
    }

    public static CompletableFuture<String> downloadInvoices(String invoice) {
        return CompletableFuture.supplyAsync(() -> {
            log.info("Downloading invoice:{}", invoice);
            try {
                Thread.sleep(new Random().nextInt(1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "Downloaded invoice: " + invoice;
        });
    }

    private static void downloadInvoices() throws ExecutionException, InterruptedException {
        List<String> list = List.of("#111", "#222", "#333");
        CompletableFuture[] cfs = list.stream()
                .map(CustomerAsyncsV2::downloadInvoices)
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(cfs).get();
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 接收上一步的结果，链接两个依赖的CF
        System.out.println(fetchOrder("customer-1").thenCompose(CustomerAsyncsV2::computeTotal).get());

        // 用于合并两个独立的CF，在两个CF都完成后被触发
        System.out.println(computeTotal("order-1").thenCombine(packProducts("order-1"), (total, products) -> {
            return "Total: " + total + " | " + products;
        }).get());

        // 如果不需要合并两个结果可以使用thenAcceptBoth
        computeTotal("order-1").thenAcceptBoth(packProducts("order-1"), (total, products) -> {
            System.out.println("Total: " + total + " | " + products);
        });

        downloadInvoices();
    }
}
