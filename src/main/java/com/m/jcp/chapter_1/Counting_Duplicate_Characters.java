package com.m.jcp.chapter_1;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhangtian1
 */
public class Counting_Duplicate_Characters {

    public static void main(String[] args) {
        Map<Character, Integer> resultV1 = countDuplicateCharactersV1("Hello World!");
        System.out.println(resultV1);

        Map<Character, Long> resultV2 = countDuplicateCharactersV2("This is a test string!");
        System.out.println(resultV2);
    }

    public static Map<Character, Integer> countDuplicateCharactersV1(String str) {
        Map<Character, Integer> result = new HashMap<>();
        for (var c : str.toCharArray()) {
            result.compute(c, (k, v) -> (v == null) ? 1 : ++v);
        }
        return result;
    }

    public static Map<Character, Long> countDuplicateCharactersV2(String str) {
        // 返回一个IntStream，它的元素是str中每个字符的int值
        var strIntStream = str.chars();
        // 返回一个Stream<Character>，它的元素是str中每个字符
        var characterStream = strIntStream.mapToObj(c -> (char) c);
        return characterStream.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

}
