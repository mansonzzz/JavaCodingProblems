package com.m.jcp.chapter_9;

import java.util.function.Function;

/**
 * @author zhangtian1
 */
public class Composing_functions {
    public static void main(String[] args) {
        Function<String, String> introFunc = Editors::addIntro;
        Function<String, String> bodyFunc = Editors::addBody;
        Function<String, String> conclusionFunc = Editors::addConclusion;

        Function<String, String> article = introFunc.andThen(bodyFunc).andThen(conclusionFunc);
        System.out.println(article.apply("Test article\n"));
    }
}

class Editors {
    public Editors() {
    }

    public static String addIntro(String article) {
        return article + "This is an article about ...\n";
    }

    public static String addBody(String article) {
        return article + "This is the body of the article ...\n";
    }

    public static String addConclusion(String article) {
        return article + "This is the conclusion of the article ...\n";
    }
}
