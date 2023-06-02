package com.m.jcp.chapter_1;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhangtian1
 */
public class Sorting_an_array_of_strings_by_length {
    public static void main(String[] args) {
        String[] strs = {"a", "bbbbbbbbbbb", "ccc", "dddd", "eeeee"};
        String[] result1 = sortArrayByLength(strs, true);
        System.out.println(Arrays.toString(result1));

        String[] result2 = sortArrayByLength(strs, false);
        System.out.println(Arrays.toString(result2));
    }

    public static String[] sortArrayByLength(String[] strs, Boolean isAsc) {
        if (isAsc) {
            return Arrays.stream(strs).sorted(Comparator.comparingInt(String::length)).toArray(String[]::new);
        } else {
            return Arrays.stream(strs).sorted(Comparator.comparingInt(String::length).reversed()).toArray(String[]::new);
        }
    }
}
