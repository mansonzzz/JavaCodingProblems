package com.m.jcp.chapter_1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author zhangtian1
 */
public class Generating_all_permutations {

    private static final String TEMPLATE = ">>> prefix:%s,str:%s";

    private static final String INIT = "";

    private static final String TXT = "test";

    public static void main(String[] args) {
//        System.out.println(permuteAndPrintV1(TXT)); // [test, tets, tste, tset, ttes, ttse, estt, estt, etts, etst, etst, etts, stte, stet, stet, stte, sett, sett, ttes, ttse, test, tets, tste, tset]
//        System.out.println(permuteAndPrintV2(TXT)); // [estt, etts, test, ttse, stte, tets, tste, stet, sett, tset, ttes, etst]
//        System.out.println(permuteAndPrintV3("a")); // [etts, estt, test, ttse, stte, tets, tste, stet, sett, tset, ttes, etst]
    }

    public static List<String> permuteAndPrintV1(String str) {
        return permuteAndPrintV1(INIT, str);
    }

    /**
     * @param PREFIX 已排列的字符串
     * @param str    待排列的字符串
     */
    private static List<String> permuteAndPrintV1(String PREFIX, String str) {
        var result = new ArrayList<String>();
        var n = str.length();
        if (str.length() == 0) {
//            System.out.println(PREFIX);
            result.add(PREFIX);
        } else {
            for (var i = 0; i < n; i++) {
                var PRE = PREFIX + str.charAt(i);
                var STR = str.substring(i + 1, n) + str.substring(0, i);
//                System.out.printf((TEMPLATE) + "%n", PRE, STR);
                result.addAll(permuteAndPrintV1(PRE, STR));
            }
        }
        return result;
    }

    public static Set<String> permuteAndPrintV2(String str) {
        return permuteAndPrintV2("", str);
    }

    /**
     * 使用 Set 进行去重
     */
    public static Set<String> permuteAndPrintV2(String PREFIX, String str) {
        var result = new HashSet<String>();
        var n = str.length();
        if (str.length() == 0) {
            result.add(PREFIX);
//            System.out.println(PREFIX);
        } else {
            for (var i = 0; i < n; i++) {
                var PRE = PREFIX + str.charAt(i);
                var STR = str.substring(i + 1, n) + str.substring(0, i);
//                System.out.printf((TEMPLATE) + "%n", PRE, STR);
                result.addAll(permuteAndPrintV2(PRE, STR));
            }
        }
        return result;
    }

    public static Stream<String> permuteAndPrintV3_1(String str) {
        // 必须，否则会返回一个空的流导致后续的 flatMap 无法执行
        if (str.isEmpty()) {
            return Stream.of("");
        }
        return IntStream.range(0, str.length())
                .parallel()
                .boxed()
                .flatMap(i -> permuteAndPrintV3_1(str.substring(0, i) + str.substring(i + 1))
                        .map(c -> str.charAt(i) + c)
                );
    }

}
