package com.m.jcp.chapter_8.factory_pattern;

/**
 * @author zhangtian1
 */
public class Main {
    public static void main(String[] args) {
        Gac gac = (Gac) MelonFactory.newInstance(Gac.class);
        Melon melon = (Melon) MelonFactory.newInstance("a", 1, "red");

        System.out.println(gac);
        System.out.println(melon);
    }
}
