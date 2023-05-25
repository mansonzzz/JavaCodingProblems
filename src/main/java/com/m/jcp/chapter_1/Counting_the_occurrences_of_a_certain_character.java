package com.m.jcp.chapter_1;

import com.google.common.base.CharMatcher;
import org.apache.commons.lang3.StringUtils;

/**
 * @author zhangtian1
 */
public class Counting_the_occurrences_of_a_certain_character {

    public static void main(String[] args) {
        String str = "hello world";
        char ch = 'l';
        System.out.println(countOccurrencesOfACertainCharacterV1(str,ch));

        countOccurrencesOfACertainCharacterV2(str,ch);
    }

    public static long countOccurrencesOfACertainCharacterV1(String str,char ch){
        return str.chars().filter(c -> c == ch).count();
    }

    /**
     * Third-party libraries
     * Apache Commons Lang - StringUtils.countMatches()
     * Spring Framework - StringUtils.countOccurrencesOf()
     * Guava - CharMatcher.is().countIn()
     */
    public static void countOccurrencesOfACertainCharacterV2(String str,char ch){
        int lang3Count = StringUtils.countMatches(str, ch);
        int guavaCount = CharMatcher.is(ch).countIn(str);
        assert lang3Count == 3;
        assert guavaCount == 3;
    }

}
