package com.m.jcp.chapter_10.pool;

/**
 * @author zhangtian1
 * <p>
 * 为了优化线程池的大小，我们需要收集以下信息：
 * <ul>
 *     <li>CPU的数量（通过Runtime.getRuntime().availableProcessors()获取）</li>
 *     <li>目标CPU利用率（在0到1的范围内）</li>
 *     <li>等待时间（W）</li>
 *     <li>计算时间（C）</li>
 * </ul>
 * <p>
 * 以下公式可以帮助我们确定线程池的最优大小：
 * <p>
 * 线程数量 = CPU数量 * 目标CPU利用率 * (1 + W/C)
 * <p>
 * <p>
 * 作为经验法则，对于计算密集型任务（通常是小任务），将线程池的线程数量设置为CPU数量或CPU数量+1（以防止可能的暂停）可能是个好主意。
 * <p>
 * 对于耗时且阻塞的任务（例如，I/O操作），更大的线程池更好，因为线程将无法以高速率进行调度。
 * <p>
 * 同时，也要注意与其他线程池（例如，数据库连接池和套接字连接池）的干扰。
 */
public class Main {
    public static void main(String[] args) {
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        System.out.println("availableProcessors: " + availableProcessors);
    }
}
