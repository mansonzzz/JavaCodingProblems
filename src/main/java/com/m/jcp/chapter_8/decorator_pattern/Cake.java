package com.m.jcp.chapter_8.decorator_pattern;

/**
 * @author zhangtian1
 */
public class Cake {
    private final String decorations;

    public Cake(String decorations) {
        this.decorations = decorations;
    }

    public Cake decorate(String decoration) {
        return new Cake(getDecorations() + " " + decoration);
    }

    public String getDecorations() {
        return decorations;
    }
}
