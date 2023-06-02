package com.m.jcp.chapter_1;

/**
 * @author zhangtian1
 */
public class Finding_the_longest_common_prefix {
    public static void main(String[] args) {
        String[] texts = {"abc", "abcd", "abcde", "ab", "abcd", "abcdef"};
        System.out.println(longestCommonPrefix(texts));
    }

    private static String longestCommonPrefix(String[] strs) {
        if (strs.length == 1) {
            return strs[0];
        }
        int firstLen = strs[0].length();
        for (int prefixLen = 0; prefixLen < firstLen; prefixLen++) {
            char ch = strs[0].charAt(prefixLen);
            for (int i = 1; i < strs.length; i++) {
                if (prefixLen >= strs[i].length() || strs[i].charAt(prefixLen) != ch) {
                    return strs[i].substring(0, prefixLen);
                }
            }
        }
        return strs[0];
    }
}
