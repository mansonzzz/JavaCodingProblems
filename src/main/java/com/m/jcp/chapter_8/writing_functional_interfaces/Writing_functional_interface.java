package com.m.jcp.chapter_8.writing_functional_interfaces;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangtian1
 * 将行为作为参数传递
 */
public class Writing_functional_interface {
    public static void main(String[] args) {
        List<Melon> melons = new ArrayList<>();
        FiltersV1.filter(melons, melon -> "watermelon".equalsIgnoreCase(melon.getType()))
                .forEach(System.out::println);

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        FiltersV2.filter(integers, integer -> integer % 2 == 0)
                .forEach(System.out::println);
    }

    private static List<Melon> filterByType(List<Melon> melons, String type) {
        List<Melon> result = new ArrayList<>();
        for (Melon melon : melons) {
            if (melon != null && type.equalsIgnoreCase(melon.getType())) {
                result.add(melon);
            }
        }
        return result;
    }
}
