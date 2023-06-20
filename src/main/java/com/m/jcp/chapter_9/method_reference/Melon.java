package com.m.jcp.chapter_9.method_reference;

/**
 * @author zhangtian1
 */
public record Melon(String type, Integer weight) {

    public static Melon growingWeight(Melon melon) {
        return new Melon(melon.type(), melon.weight() + 1000);
    }

}
