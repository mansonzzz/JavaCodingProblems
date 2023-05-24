package com.m.jcp.chapter_1;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author zhangtian1
 */
public class Reversing_letters_and_words {

    private static final String WHITE_SPACE = " ";

    private static final Pattern PATTERN = Pattern.compile(" +");

    public static void main(String[] args) {
        System.out.println(reversingLettersAndWordsV1("this is test"));
        System.out.println(reversingLettersAndWordsV2("this is test"));
    }

    public static String reversingLettersAndWordsV1(String str) {
        var splitResult = str.split(WHITE_SPACE);
        var reverseResult = new StringBuilder();
        for (var word : splitResult) {
            var wordResult = new StringBuilder();
            for (var j = word.length(); j > 0; j--) {
                wordResult.append(word.charAt(j - 1));
            }
            reverseResult.append(wordResult).append(WHITE_SPACE);
        }
        return reverseResult.toString();
    }

    public static String reversingLettersAndWordsV2(String str) {
        return PATTERN.splitAsStream(str)
                .map(word -> new StringBuilder(word).reverse())
                .collect(Collectors.joining(WHITE_SPACE));
    }

}
