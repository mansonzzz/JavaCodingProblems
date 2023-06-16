package com.m.jcp.chapter_8.template_method_pattern;

/**
 * @author zhangtian1
 */
public class Main {
    public static void main(String[] args) {
        Pizza p = new Pizza();
        new PizzaMaker().make(p, (Pizza pizza) -> {
            System.out.println(">>> add top ingredients");
        });
    }
}
