package com.m.jcp.chapter_5.sorting_an_array;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author zhangtian1
 */
public class Insertion_sort {
    public static void main(String[] args) {
//        int[] integers = {5, 4, 3, 2, 1};
//        insertionSortV1(integers);
//        insertionSortV2(integers);
//        System.out.println(Arrays.toString(integers));
        Melon melonA = new Melon("a", 1);
        Melon melonB = new Melon("b", 2);
        Melon melonC = new Melon("c", 3);
        Melon melonD = new Melon("d", 4);
        Melon melonE = new Melon("e", 5);
        Melon[] melons = {melonE, melonD, melonC, melonB, melonA};
        insertionObj(melons, Comparator.comparingInt(Melon::getWeight));
        System.out.println(Arrays.toString(melons));
    }

    /**
     * [4, 5, 3, 2, 1]
     * [4, 3, 5, 2, 1]
     * [3, 4, 5, 2, 1]
     * [3, 4, 2, 5, 1]
     * [3, 2, 4, 5, 1]
     * [2, 3, 4, 5, 1]
     * [2, 3, 4, 1, 5]
     * [2, 3, 1, 4, 5]
     * [2, 1, 3, 4, 5]
     * [1, 2, 3, 4, 5]
     * [1, 2, 3, 4, 5]
     */
    public static void insertionSortV1(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                int temp = arr[j + 1];
                arr[j + 1] = arr[j];
                arr[j] = temp;
                j--;
                System.out.println(Arrays.toString(arr));
            }
        }
    }

    /**
     * [5, 5, 3, 2, 1]
     * [4, 5, 5, 2, 1]
     * [4, 4, 5, 2, 1]
     * [3, 4, 5, 5, 1]
     * [3, 4, 4, 5, 1]
     * [3, 3, 4, 5, 1]
     * [2, 3, 4, 5, 5]
     * [2, 3, 4, 4, 5]
     * [2, 3, 3, 4, 5]
     * [2, 2, 3, 4, 5]
     * [1, 2, 3, 4, 5]
     */
    public static void insertionSortV2(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
                System.out.println(Arrays.toString(arr));
            }
            // 减少额外赋值操作
            arr[j + 1] = key;
        }
    }

    public static <T> void insertionObj(T[] arr, Comparator<T> c) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            T key = arr[i];
            int j = i - 1;
            while (j >= 0 && c.compare(arr[j], key) > 0) {
                arr[j + 1] = arr[j];
                j--;
                System.out.println(Arrays.toString(arr));
            }
            arr[j + 1] = key;
        }
    }

}
