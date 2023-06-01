package com.m.jcp.chapter_1;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author zhangtian1
 */
public class Finding_the_character_with_the_most_appearances {

    private static final String str = "hello world!";

    private static final String TEMPLATE = ">>> Character: %s, Count: %s";

    public static void main(String[] args) {
        Pair<Character, Integer> result1 = maxOccurrenceCharacterV1(str);
        System.out.printf((TEMPLATE) + "%n", result1.getLeft(), result1.getRight());

        Pair<Character, Integer> result2 = maxOccurrenceCharacterV2(str);
        System.out.printf((TEMPLATE) + "%n", result2.getLeft(), result2.getRight());

        Pair<Character, Long> result3 = maxOccurrenceCharacterV3(str);
        System.out.printf((TEMPLATE) + "%n", result3.getLeft(), result3.getRight());
    }

    public static Pair<Character, Integer> maxOccurrenceCharacterV1(String str) {
        Map<Character, Integer> counter = new HashMap<>();
        char[] charArray = str.toCharArray();
        Integer max = 0;
        char maxChar = Character.MIN_VALUE;
        for (char c : charArray) {
            if (Character.isWhitespace(c)) {
                continue;
            }
            counter.put(c, counter.getOrDefault(c, 0) + 1);
            if (counter.get(c) > max) {
                max = counter.get(c);
                maxChar = c;
            }
        }
        return Pair.of(maxChar, max);
    }

    public static Pair<Character, Integer> maxOccurrenceCharacterV2(String str) {
        int max = 0;
        char maxChar = Character.MIN_VALUE;

        int[] counter = new int[256];
        char[] charArray = str.toCharArray();
        Arrays.fill(counter, 0);

        for (char c : charArray) {
            if (Character.isWhitespace(c)) {
                continue;
            }
            counter[c]++;
            if (counter[c] > max) {
                max = counter[c];
                maxChar = c;
            }
        }
        return Pair.of(maxChar, max);
    }

    public static Pair<Character, Long> maxOccurrenceCharacterV3(String str) {
        return str.chars()
                .filter(c -> !Character.isWhitespace(c))
                .mapToObj(c -> (char) c)
                .collect(groupingBy(c -> c, Collectors.counting()))
                .entrySet()
                .stream()
                .max(comparingByValue())
                .map(e -> Pair.of(e.getKey(), e.getValue()))
                .orElse(Pair.of(Character.MIN_VALUE, -1L));
    }
}
