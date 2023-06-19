package com.m.jcp.chapter_9.infinite_stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * @author zhangtian1
 */
public class Infinite_sequential_ordered_stream {
    public static void main(String[] args) {
        // V1 use limit
        Stream.iterate(1, i -> i + 1).limit(10).forEach(System.out::println);
        System.out.println(">>>>> <<<<<");

        // V2 use take while
        Stream.iterate(1, i -> i + 1)
                .takeWhile(i -> i <= 10)
                .forEach(System.out::println);
        System.out.println(">>>>> <<<<<");

        // V3 use predicate
        Stream.iterate(1, i -> i <= 10, i -> i + 1).forEach(System.out::println);
        System.out.println(">>>>> <<<<<");

        // V4 should use limit to avoid infinite stream
        Stream.iterate(1, i -> i <= 10, i -> predicate(i) ? genT() : genF())
                .limit(10)
                .forEach(System.out::println);
    }

    private static boolean predicate(int i) {
        return i + i % 2 == 0;
    }

    private static int genT() {
        return new Random().nextInt(20);
    }

    private static int genF() {
        return -1 * new Random().nextInt(10);
    }
}
