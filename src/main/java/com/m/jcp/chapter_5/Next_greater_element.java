package com.m.jcp.chapter_5;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author zhangtian1
 */
public class Next_greater_element {
    public static void main(String[] args) {
        int[] integers = {1, 2, 3, 4, 12, 2, 1, 4};
//        ngeV1(integers);
        System.out.println(Arrays.toString(ngeV2(integers)));
        ;
    }

    private static void ngeV1(int[] arr) {
        int nge;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            nge = -1;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    nge = arr[j];
                    break;
                }
            }
            System.out.println(arr[i] + "-nge:" + nge);
        }
    }

    private static int[] ngeV2(int[] arr) {
        int n = arr.length;
        Stack<Integer> stack = new Stack<>();
        int[] nge = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            nge[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
        }
        return nge;
    }
}
