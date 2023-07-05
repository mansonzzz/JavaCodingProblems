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

    public static void fetchAndPrintOrder() throws ExecutionException, InterruptedException {
        ThreadFactory basicThreadFactory = new BasicThreadFactory.Builder()
                .namingPattern("Customer-Asyncs-%d").build();
        ExecutorService executor = Executors.newSingleThreadExecutor(basicThreadFactory);

        CompletableFuture<Void> cfOrderSummary = CompletableFuture.supplyAsync(() -> {
            log.info(">>> Fetch order by:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "summary-1";
        }).thenAcceptAsync(o -> log.info(">>> Print order:{} by {}", o, Thread.currentThread().getName()), executor);
        cfOrderSummary.get();
        executor.shutdownNow();
    }

    /**
     * 使用thenApply()方法，会阻塞主线程，直到cfOrderSummary.get()执行完毕
     * 这个方法的日志会将Print order2和Print order1都打印出来
     */
    public static void fetchNonChainCallUseThenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cfOrderSummary = CompletableFuture.supplyAsync(() -> {
            log.info(">>> Fetch order by:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "summary-1";
        });
        cfOrderSummary.thenApply(o -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info(">>> Print order1:{} by {}", o, Thread.currentThread().getName());
            return o + " summary-2";
        });
        cfOrderSummary.thenApply(o -> {
            log.info(">>> Print order2:{} by {}", o, Thread.currentThread().getName());
            return o + " summary-3";
        });
        cfOrderSummary.get();
    }

    /**
     * 使用thenApplyAsync()方法，不会阻塞主线程
     * 由于第一个回调中调用了sleep方法，所以这个方法的日志只会将Print order2打印出来
     */
    public static void fetchNonChainCallUseThenApplyAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> cfOrderSummary = CompletableFuture.supplyAsync(() -> {
            log.info(">>> Fetch order by:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "summary-1";
        });
        cfOrderSummary.thenApplyAsync(o -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            log.info(">>> Print order1:{} by {}", o, Thread.currentThread().getName());
            return o + " summary-2";
        });
        cfOrderSummary.thenApplyAsync(o -> {
            log.info(">>> Print order2:{} by {}", o, Thread.currentThread().getName());
            return o + " summary-3";
        });
        cfOrderSummary.get();
    }

    public static void deliverOrderNotifyCustomer() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> cfOrderSummary = CompletableFuture.supplyAsync(() -> {
            log.info(">>> Fetch order by:{}", Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            return "summary-1";
        }).thenRun(() -> log.info(">>> Notify customer by:{}", Thread.currentThread().getName()));
        cfOrderSummary.get();
    }

    public static void fetchV1() {
        ExecutorService e = Executors.newSingleThreadExecutor(r -> new Thread(r, "CS-1"));
        CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()); // CS-1
            return 42;
        }, e).thenApply(i -> {
            // 回调是在调用thenApply的线程(main)上执行的
            System.out.println(Thread.currentThread().getName()); // main
            return i.toString();
        });
        e.shutdownNow();
    }

    /**
     * CS-1 -> main -> hello!
     * 客户端线程被阻塞，此处为调用thenApply的线程(main)
     */
    public static void fetchV2() {
        ExecutorService e = Executors.newSingleThreadExecutor(r -> new Thread(r, "CS-1"));
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName()); // CS-1
            return 42;
        }, e);
        // 在调用thenApply前，f已经完成
        CompletableFuture<String> f2 = f.thenApply(i -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println(Thread.currentThread().getName()); // main
            return i.toString();
        });
        System.out.println("hello!");
        e.shutdownNow();
    }

    public static void fetchV3() {
        CompletableFuture.supplyAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(Thread.currentThread().getName());
                    return 42;
                })
                // 在注册thenApply前，f未完成(此时会由上一个线程执行)
                .thenRun(() -> System.out.println(Thread.currentThread().getName())).join();
    }
}
