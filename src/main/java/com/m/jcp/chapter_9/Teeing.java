package com.m.jcp.chapter_9;

import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * @author zhangtian1
 */
public class Teeing {
    public static void main(String[] args) {
        CountSum resultOfCountSum = Stream.of(2, 3, 5, 6, 7, 9)
                .collect(teeing(counting(), summingInt(e -> e), CountSum::new));
        System.out.println(resultOfCountSum);

        // @formatter:off
        MinMax resultOfMinMax = Stream.of(1, 19, 10, 22, -1, 0, 13)
                .collect(teeing(
                        minBy(Integer::compareTo), // return Optional<Integer>
                        maxBy(Integer::compareTo), // return Optional<Integer>
                        (min, max) -> new MinMax(min.orElse(null), max.orElse(null)))
                );
        // @formatter:on
        System.out.println(resultOfMinMax);
    }

    record CountSum(Long count, Integer sum) {
    }

    record MinMax(Integer min, Integer max) {
    }
}
