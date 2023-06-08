package com.m.jcp.chapter_5;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhangtian1
 */
public class Finding_an_element_in_an_array {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        assert containsElement(arr, 2);
        assert !containsElement(arr, 4);

        String[] strArr = {"a", "b", "c"};
        assert containsObjElementV1(strArr, "a", String::compareTo);
        assert !containsObjElementV1(strArr, "d", String::compareTo);
    }

    public static boolean containsElement(int[] arr, int toContain) {
        return Arrays.stream(arr).anyMatch(i -> i == toContain);
    }

    public static <T> boolean containsObjElementV1(T[] arr, T clazz, Comparator<T> comparator) {
        return Arrays.stream(arr).anyMatch(i -> comparator.compare(i, clazz) == 0);
    }

}
