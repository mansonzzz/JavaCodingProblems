package com.m.jcp.chapter_5.sorting_an_array;

import java.util.Arrays;

/**
 * @author zhangtian1
 */
public class Bubble_sort {
    public static void main(String[] args) {
        int[] integers = {5, 4, 3, 2, 1};
        // BEST CASE: O(n)
        // AVERAGE CASE: O(n^2)å
        // WORST CASE: O(n^2)
//        bubbleSort(integers);
//        bubbleSortOptimized(integers);
        cockTailSort(integers);
//        System.out.println(Arrays.toString(integers));
    }

    /**
     * [4, 5, 3, 2, 1]
     * [4, 3, 5, 2, 1]
     * [4, 3, 2, 5, 1]
     * [4, 3, 2, 1, 5]
     * [3, 4, 2, 1, 5]
     * [3, 2, 4, 1, 5]
     * [3, 2, 1, 4, 5]
     * [2, 3, 1, 4, 5]
     * [2, 1, 3, 4, 5]
     * [1, 2, 3, 4, 5]
     * [1, 2, 3, 4, 5]
     */
    public static void bubbleSort(int[] arr) {
        var arrLength = arr.length;
        for (var i = 0; i < arrLength - 1; i++) {
            for (var j = 0; j < arrLength - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    var temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                System.out.println(Arrays.toString(arr));
            }
        }
    }

    /**
     * 对于100000个Integer的测试用例，优化版本会快2s左右
     * 可以提高效率，但没法降低时间复杂度
     * [4, 5, 3, 2, 1]
     * [4, 3, 5, 2, 1]
     * [4, 3, 2, 5, 1]
     * [4, 3, 2, 1, 5]
     * [3, 4, 2, 1, 5]
     * [3, 2, 4, 1, 5]
     * [3, 2, 1, 4, 5]
     * [2, 3, 1, 4, 5]
     * [2, 1, 3, 4, 5]
     * [1, 2, 3, 4, 5]
     */
    public static void bubbleSortOptimized(int[] arr) {
        var n = arr.length;
        while (n != 0) {
            int swap = 0;
            for (var i = 1; i < n; i++) {
                if (arr[i] < arr[i - 1]) {
                    int temp = arr[i];
                    arr[i] = arr[i - 1];
                    arr[i - 1] = temp;
                }
                // 记录最后一次交换的位置，这个位置之后的所有元素在下一次迭代中都不需要再被考虑
                swap = i;
            }
            n = swap;
        }
    }

    /**
     * [4, 5, 3, 2, 1]
     * [4, 3, 5, 2, 1]
     * [4, 3, 2, 5, 1]
     * [4, 3, 2, 1, 5]
     * [4, 3, 1, 2, 5]
     * [4, 1, 3, 2, 5]
     * [1, 4, 3, 2, 5]
     * [1, 3, 4, 2, 5]
     * [1, 3, 2, 4, 5]
     * [1, 2, 3, 4, 5]
     * <p>
     * AVERAGE CASE: O(n^2)
     * BEST CASE: O(n)
     * WORST CASE: O(n^2)
     */
    private static void cockTailSort(int[] arr) {
        int temp;
        int j, left = 0, right = arr.length - 1;
        boolean cocktail;
        do {
            cocktail = false;
            for (j = left; j < right; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // 有元素交换，所以不是有序，标记变量改变
                    cocktail = true;
                }
                System.out.println(Arrays.toString(arr));
            }
            right--;
            for (j = right; j > left; j--) {
                if (arr[j] < arr[j - 1]) {
                    temp = arr[j];
                    arr[j] = arr[j - 1];
                    arr[j - 1] = temp;
                }
                System.out.println(Arrays.toString(arr));
            }
            left++;

        } while (left < right && cocktail);
    }
}
