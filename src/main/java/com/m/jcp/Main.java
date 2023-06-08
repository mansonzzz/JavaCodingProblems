package com.m.jcp;

/**
 * @author zhangtian1
 */
public class Main {

    private static final String TEMPLATE = "[%s](%s.java)";

    public static void main(String[] args) {
        String title = "TITLE";
        String clazzName = title.replaceAll(" ", "_");
        System.out.println(clazzName);
        System.out.printf((TEMPLATE) + "%n", title.toLowerCase(), clazzName);
    }
}
