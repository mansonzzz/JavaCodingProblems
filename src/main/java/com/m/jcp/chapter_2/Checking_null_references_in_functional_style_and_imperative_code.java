package com.m.jcp.chapter_2;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author zhangtian1
 */
public class Checking_null_references_in_functional_style_and_imperative_code {
    private static final String TEMPLATE = ">>> integers contains null?[%s]";

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, null, 3, 4, null, 6, 7, 8);
        System.out.printf((TEMPLATE) + "%n", integersContainsNulls(numbers));

        System.out.println(sumIntegers(numbers));
    }

    private static int sumIntegers(List<Integer> integers) {
        if (Objects.isNull(integers)) {
            throw new IllegalArgumentException("List cannot be null");
        }
        return integers.stream()
                .filter(Objects::nonNull)
                .mapToInt(Integer::intValue).sum();
    }

    private static boolean integersContainsNulls(List<Integer> integers) {
        if (integers == null)
            return false;
        return integers.stream().anyMatch(Objects::isNull);
    }

}
