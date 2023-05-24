package com.m.jcp.chapter_1;

/**
 * @author zhangtian1
 */
public class Checking_whether_a_string_contains_only_digits {

    public static void main(String[] args) {
        String strContainsOnlyDigits = "123456";
        String strContainsLetters = "123456a";
        // Avoid: 使用parseInt()/parseLong()来解决问题，不应该通过捕获NumberFormatException异常来处理业务逻辑
        // Third-party library: Apache Commons Lang - StringUtils.isNumeric()
        assert containsOnlyDigitsV1(strContainsOnlyDigits);
        assert !containsOnlyDigitsV1(strContainsLetters);

        assert containsOnlyDigitsV2(strContainsOnlyDigits);
        assert !containsOnlyDigitsV2(strContainsLetters);
    }

    public static boolean containsOnlyDigitsV1(String str) {
        return str.matches("[0-9]+");
    }

    public static boolean containsOnlyDigitsV2(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

}
