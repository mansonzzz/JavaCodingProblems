package com.m.jcp.chapter_1;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author zhangtian1
 */
public class Joining_multiple_strings_with_a_delimiter {

    public static final char DELIMETER = '$';

    public static final String[] ARGS = {"a", "b", "c"};

    public static void main(String[] args) {
        var result1 = joinByDelimiterV1(DELIMETER, ARGS);
        assert Objects.equals(result1, "a$b$c");

        var result2 = joinByDelimiterV2(DELIMETER, ARGS);
        assert Objects.equals(result2, "a$b$c");

        var result3 = joinByDelimiterV3(DELIMETER, ARGS);
        assert Objects.equals(result3, "a$b$c");

        // AVOID
        var result4 = joinByDelimiterV4(DELIMETER, ARGS);
        assert Objects.equals(result4, "a$b$c");
    }

    public static String joinByDelimiterV1(char delimiter, String... args) {
        var joiner = new StringJoiner(String.valueOf(delimiter));
        for (var arg : args) {
            joiner.add(arg);
        }
        return joiner.toString();
    }

    public static String joinByDelimiterV2(char delimiter, String... args) {
        return String.join(String.valueOf(delimiter), args);
    }

    public static String joinByDelimiterV3(char delimiter, String... args) {
        return Arrays.stream(args, 0, args.length)
                .collect(Collectors.joining(String.valueOf(delimiter)));
    }

    /**
     * AVOID:不建议通过字符串拼接的方式来实现，这样会产生很多临时对象，影响性能
     */
    public static String joinByDelimiterV4(char delimiter, String... args) {
        String str = "";
        for (var arg : args) {
            str += arg + delimiter;
        }
        return str.substring(0, str.length() - 1);
    }

}
