package com.m.jcp.chapter_7.getting_the_annotation_of_a_receiver_type;

import java.util.Objects;

/**
 * @author zhangtian1
 */
public class Melon {
    private final String type;
    private final int weight;

    public Melon(String type, int weight) {
        this.type = type;
        this.weight = weight;
    }

    public void eat(@Ripe Melon this) {
        System.out.println("Eating " + this);
    }

    public String getType() {
        return type;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Melon{" +
               "type='" + type + '\'' +
               ", weight=" + weight +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Melon melon = (Melon) o;
        return weight == melon.weight && Objects.equals(type, melon.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, weight);
    }
}
