package com.m.jcp.chapter_8.decorator_pattern;

/**
 * @author zhangtian1
 */
public class Main {
    public static void main(String[] args) {
        CakeDecorator decorator = new CakeDecorator(
                // pass multiple behaviors to the constructor
                (Cake c) -> c.decorate("with Nuts"),
                (Cake c) -> c.decorate("with Cream")
        );
        Cake cake = decorator.decorate(new Cake("Base cake"));
        // Base cake with Nuts with Cream
        System.out.println(cake.getDecorations());
    }
}
