package com.m.jcp.chapter_1;

import io.vavr.Tuple;
import io.vavr.Tuple2;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author zhangtian1
 */
public class Counting_vowels_and_consonants {

    private static final Set<Character> allVowels = Set.of('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) {
        var str = "this is test";
        var result1 = countVowelsAndConsonantsV1(str);
        var result2 = countVowelsAndConsonantsV2(str);
        System.out.println(result1);
        System.out.println(result2);
    }

    public static Tuple2<Integer, Integer> countVowelsAndConsonantsV1(String str) {
        str = str.toLowerCase();
        int vowels = 0;
        int consonants = 0;
        for (int i = 0; i < str.length(); i++) {
            var ch = str.charAt(i);
            if (allVowels.contains(ch)) {
                vowels++;
            } else if ((ch >= 'a' && ch <= 'z')) {
                consonants++;
            }
        }
        return Tuple.of(vowels, consonants);
    }

    public static Tuple2<Integer, Integer> countVowelsAndConsonantsV2(String str) {
        Map<Boolean, Long> result = str.chars()
                .mapToObj(ch -> (char) ch)
                .filter(ch -> ch >= 'a' && ch <= 'z')
                .collect(Collectors.groupingBy(allVowels::contains, Collectors.counting()));
        return Tuple.of(result.get(true).intValue(), result.get(false).intValue());
    }
}
