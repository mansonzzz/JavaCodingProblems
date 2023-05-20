package com.m.jcp.chapter_1;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhangtian1
 */
public class Counting_Duplicate_Characters {

    public static void main(String[] args) {
        // { =1, !=1, r=1, d=1, e=1, W=1, H=1, l=3, o=2}
        var resultV1 = countDuplicateCharactersV1("Hello World!");
        System.out.println(resultV1);

        // { =4, !=1, a=1, r=1, s=4, t=3, T=1, e=1, g=1, h=1, i=3, n=1}
        var resultV2 = countDuplicateCharactersV2("This is a test string!");
        System.out.println(resultV2);

        // {🐦=1, 😀=1, 🐒=1}
        var resultV3 = countDuplicateCharactersV3("😀🐦🐒");
        System.out.println(resultV3);

        // {🐦=1, 😀=1, 🐒=1}
        var resultV4 = countDuplicateCharactersV4("😀🐦🐒");
        System.out.println(resultV4);

    }

    public static Map<Character, Integer> countDuplicateCharactersV1(String str) {
        var result = new HashMap<Character, Integer>();
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

    /**
        早期的 Unicode 版本中，字符集的大小是 16 位，因此可以用 char 类型表示一个字符。
        但是，随着 Unicode 的发展，字符集的大小已经超过了 16 位。
        Unicode 找到了一种仍然使用 16 位来表示这些字符的解决方案 - [代理对]
        Java 可以使用 codePointAt ()，codePoints ()，codePointCount ()，和 offsetByCodePoints () 方法来处理代理对。
     */
    public static Map<String, Integer> countDuplicateCharactersV3(String str) {
        var result = new HashMap<String,Integer>();
        for (var i = 0; i < str.length(); i++) {
            // 指定索引处字符的 Unicode 值
            var unicodeValue = str.codePointAt(i);
            var character = String.valueOf(Character.toChars(unicodeValue));
            // 如果是代理对，i 多加一次 1
            if(Character.charCount(unicodeValue) == 2) {
                i++;
            }
            result.compute(character, (k, v) -> (v == null) ? 1 : ++v);
        }
        return result;
    }

    public static Map<String, Long> countDuplicateCharactersV4(String str) {
        var intStream = str.codePoints();
        var stringStream = intStream.mapToObj(c -> String.valueOf(Character.toChars(c)));
        return stringStream.collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

}
