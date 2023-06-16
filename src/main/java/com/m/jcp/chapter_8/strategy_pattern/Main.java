package com.m.jcp.chapter_8.strategy_pattern;

/**
 * @author zhangtian1
 */
public class Main {
    public static void main(String[] args) {
        String s = "Hello*World!";
        String result = Remover.remove(s, (String str) -> str.replaceAll("\\*", " "));
        System.out.println(result);
    }
}
