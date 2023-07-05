package com.m.jcp.chapter_11.completable_future;

/**
 * @author zhangtian1
 * add `-ea` to VM options to enable assertion
 */
public class Main {
    public static void main(String[] args) throws Exception {
//        CustomerAsyncs.printOrder();
//        CustomerAsyncs.fetchOrderSummary();
        CustomerAsyncs.fetchOrderSummaryExecutor();
//        CustomerAsyncs.fetchInvoiceTotalSign();
    }
}
