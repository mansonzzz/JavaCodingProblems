package com.m.jcp.chapter_8.writing_functional_interfaces;

/**
 * @author zhangtian1
 */
public class Melon {
    private final String type;
    private final int weight;
    private final String origin;

    public Melon(String type, int weight, String origin) {
        this.type = type;
        this.weight = weight;
        this.origin = origin;
    }

    public String getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    public String getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "Melon{" +
               "type='" + type + '\'' +
               ", weight=" + weight +
               ", origin='" + origin + '\'' +
               '}';
    }
}
