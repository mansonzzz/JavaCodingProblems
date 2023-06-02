package com.m.jcp.chapter_1;

import java.util.Collections;

/**
 * @author zhangtian1
 */
public class Concatenating_the_same_string_n_times {
    public static void main(String[] args) {
//        String str = "abc";
//        int n = 3;
//        String result = concatRepeat(str, n);
//        System.out.println(result);

        String str1 = "abcabcabc";
        String str2 = "abcabccabc";
        assert hasOnlySubstrings(str1);
        assert !hasOnlySubstrings(str2);
    }

    public static String concatRepeat(String str, int n) {
        String result;
        // 1. 使用StringBuilder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(str);
        }
        result = sb.toString();

        // 2. JDK11 String.repeat
        result = str.repeat(n);

        // 3. String join
        result = String.join("", Collections.nCopies(n, str));

        return result;
    }

    /**
     * 检查输入的字符串是否完全由它的一个子串重复多次构成
     */
    public static boolean hasOnlySubstrings(String str) {
        StringBuilder sb = new StringBuilder();
        // 如果一个子字符串多次出现，那子字符串的长度不会超过总字符串长度的一半
        for (int i = 0; i < str.length() / 2; i++) {
            sb.append(str.charAt(i));
            String replaced = str.replace(sb.toString(), "");
            if (replaced.length() == 0) {
                return true;
            }
        }
        return false;
    }
}
