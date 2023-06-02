package com.m.jcp.chapter_1;

import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangtian1
 * 检查两个字符串是否是变位词
 * hello
 * ollhe
 */
public class Checking_whether_two_strings_are_anagrams {
    private static final int EXTENDED_ASCII_CODES = 256;

    public static void main(String[] args) {
        String str1 = "hello";
        String str2 = "ollhe";
        assert isAnagram(str1, str2);
        String str3 = "hello";
        String str4 = "ollhe ";
        assert isAnagram(str3, str4);
        String str5 = "hello";
        String str6 = "ollh";
        assert !isAnagram(str5, str6);
    }

    /**
     * 将第一个字符串中的字符出现次数加1，
     * 将第二个字符串中的字符出现次数减1，
     * 最后检查数组中的值是否为0
     */
    public static boolean isAnagram(String str1, String str2) {
        int[] chCounts = new int[EXTENDED_ASCII_CODES];
        char[] ch1 = StringUtils.deleteWhitespace(str1).toLowerCase().toCharArray();
        char[] ch2 = StringUtils.deleteWhitespace(str2).toLowerCase().toCharArray();
        if (ch1.length != ch2.length) {
            return false;
        }
        for (int i = 0; i < ch1.length; i++) {
            chCounts[ch1[i]]++;
            chCounts[ch2[i]]--;
        }
        for (int i = 0; i < EXTENDED_ASCII_CODES; i++) {
            if (chCounts[i] != 0) {
                return false;
            }
        }
        return true;
    }

}
