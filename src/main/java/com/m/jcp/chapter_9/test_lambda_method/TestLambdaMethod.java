package com.m.jcp.chapter_9.test_lambda_method;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

/**
 * @author zhangtian1
 */
public final class TestLambdaMethod {
    private TestLambdaMethod() {
    }

    public static final Function<String, String> firstAndLastChar = s -> s.charAt(0) + s.substring(s.length() - 1);

    public static String findFirstAndLastCharV1(String s) {
        return firstAndLastChar.apply(s);
    }

    public static List<String> rndStringFromStrings(List<String> strs) {
        //@formatter:off
        return strs
                .stream()
                .map(TestLambdaMethod::extractCharacter)
                .toList();
        //@formatter:on
    }

    /**
     * easy to test
     */
    public static String extractCharacter(String str) {
        Random rnd = new Random();
        int nr = rnd.nextInt(str.length());
        return String.valueOf(str.charAt(nr));
    }
}
