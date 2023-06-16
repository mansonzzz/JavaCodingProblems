package com.m.jcp.chapter_8.template_method_pattern;

import java.util.function.Consumer;

/**
 * @author zhangtian1
 */
public class PizzaMaker {
    public void make(Pizza pizza, Consumer<Pizza> addTopIngredients) {
        makeDough(pizza);
        addTopIngredients.accept(pizza);
        bake(pizza);
    }

    private void makeDough(Pizza p) {
        System.out.println(">>> make dough");
    }

    private void bake(Pizza p) {
        System.out.println(">>> bake");
    }
}
