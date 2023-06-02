package com.m.jcp.chapter_1;

/**
 * @author zhangtian1
 */
public class Counting_substring_occurrences_in_a_string {
    public static void main(String[] args) {
        String str = "111";
        String sub = "11";
        System.out.println(countingStringInStringV1(str, sub));
        System.out.println(countingStringInStringV2(str, sub));
    }

    public static int countingStringInStringV1(String str, String toFind) {
        int position = 0;
        int count = 0;
        int n = toFind.length();
        while ((position = str.indexOf(toFind, position)) != -1) {
            position = position + n;
            count++;
        }
        return count;
    }

    public static int countingStringInStringV2(String str, String toFind) {
        int a = str.length();
        int b = toFind.length();
        String result = str.replaceAll(toFind, "");
        int c = result.length();
        return (a - c) / b;
    }
}
