package com.m.jcp.chapter_8.strategy_pattern;

/**
 * @author zhangtian1
 */
public class Remover {

    private Remover() {
    }

    public static String remove(String s, RemoveStrategy strategy) {
        if (s == null || s.isEmpty() || strategy == null) {
            return s;
        }
        return strategy.execute(s);
    }
}
