package com.m.jcp.chapter_5.sorting_an_array;

import java.util.Arrays;

/**
 * @author zhangtian1
 * 计数排序在特定的情况下，排序效率极高。但是如果排序的计数空间范围过大，而实际元素个数非常小的情况，效率就会非常差。
 * 比如，我只有3个元素，3，1，500000，这样的情况其实是不适合用计数排序的，这一点需要注意。
 */
public class Counting_sort {
    public static void main(String[] args) {
        int[] integers = {5, 5, 4, 3, 2, 1};
        countingSort(integers);
        System.out.println("----------");
        System.out.println(Arrays.toString(integers));
    }

    /**
     * BEST CASE: O(n+k)
     * <p>
     * AVERAGE CASE: O(n+k)
     * <p>
     * WORST CASE: O(n+k)
     * <p>
     * this is a stable sort
     */
    private static void countingSort(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int n : arr) {
            if (n < min) {
                min = n;
            } else if (n > max) {
                max = n;
            }
        }
        // 节省存储空间
        int[] counts = new int[max - min + 1];
        for (int i : arr) {
            // 统计待排序数组每个元素出现次数
            counts[i - min]++;
            System.out.println(Arrays.toString(counts));
        }

        System.out.println("----------");

        int sortedIndex = 0;
        for (int i = 0; i < counts.length; i++) {
            while (counts[i] > 0) {
                // 回填
                arr[sortedIndex++] = i + min;
                counts[i]--;
                System.out.println(Arrays.toString(arr));
            }
        }
    }
}
