package com.m.jcp.chapter_1;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author zhangtian1
 */
public class Finding_the_first_non_repeated_character {
    public static void main(String[] args) {
        String str = "abaccdeff";
        System.out.println(firstNonRepeatedCharacterV1(str));
        System.out.println(firstNonRepeatedCharacterV2(str));
        System.out.println(firstNonRepeatedCharacterV3(str));
    }


    /**
     * 将字符的ASCII码作为数组下标，字符在字符串中的位置作为值，初始化为-1
     */
    public static char firstNonRepeatedCharacterV1(String str) {
        var flags = new int[256];
        Arrays.fill(flags, -1);
        int P = Integer.MAX_VALUE;
        for (var i = 0; i < str.length(); i++) {
            var ch = str.charAt(i);
            if (flags[ch] == -1) {
                flags[ch] = i;
            } else {
                flags[ch] = -2;
            }
        }
        for (int i = 0; i < 256; i++) {
            if (flags[i] >= 0) {
                P = Math.min(P, flags[i]);
            }
        }
        return P == Integer.MAX_VALUE ? Character.MIN_VALUE : str.charAt(P);
    }

    /**
     * 利用LinkedHashMap的有序性
     */
    public static char firstNonRepeatedCharacterV2(String str) {
        LinkedHashMap<Character, Integer> chars = new LinkedHashMap<>();
        for (char ch : str.toCharArray()) {
            chars.compute(ch, (k, v) -> v == null ? 1 : ++v);
        }
        for (Map.Entry<Character, Integer> entry : chars.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }
        return Character.MIN_VALUE;
    }

    /**
     * 兼容Unicode字符集
     */
    public static String firstNonRepeatedCharacterV3(String str) {
        Map<Integer, Long> chs = str.codePoints().boxed()
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        int result = chs.entrySet().stream()
                .filter(e -> e.getValue() == 1L)
                .findFirst()
                .map(Map.Entry::getKey)
                .orElse((int) Character.MIN_VALUE);
        return String.valueOf(Character.toChars(result));
    }

}
