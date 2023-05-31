package com.m.jcp.chapter_1;

import java.util.stream.IntStream;

/**
 * @author zhangtian1
 * 检查字符串是否是回文
 */
public class Checking_whether_a_string_is_a_palindrome {

    private static final String TEMPLATE = ">>> Is %s palindrome? [%s]";

    public static void main(String[] args) {
        String str = "madam";
//        String str = "abc";
        System.out.printf((TEMPLATE) + "%n", str, isPalindromeV1(str));
        System.out.printf((TEMPLATE) + "%n", str, isPalindromeV2(str));
        System.out.printf((TEMPLATE) + "%n", str, isPalindromeV2_1(str));
        System.out.printf((TEMPLATE) + "%n", str, isPalindromeV3(str));
    }

    /**
     * 从两端向中间检查
     */
    public static boolean isPalindromeV1(String str) {
        int left = 0;
        int right = str.length() - 1;

        while (right > left) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * 从中间向两端检查，减少一个变量
     */
    public static boolean isPalindromeV2(String str) {
        int n = str.length();
        for (int i = 0; i < n / 2; i++) {
            if (str.charAt(i) != str.charAt(n - i - 1)) {
                return false;
            }
        }
        return true;
    }

    /**
     * functional style
     */
    public static boolean isPalindromeV2_1(String str) {
        return IntStream.range(0, str.length() / 2)
                .noneMatch(p -> str.charAt(p) != str.charAt(str.length() - p - 1));
    }

    public static boolean isPalindromeV3(String str) {
        return str.contentEquals(new StringBuilder(str).reverse());
    }

}
