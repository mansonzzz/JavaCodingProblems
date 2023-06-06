package com.m.jcp.chapter_5.sorting_an_array;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author zhangtian1
 */
public class JDK_built_in {
    public static void main(String[] args) {
        int[] integers = {10, 2, 7, 4, 5};
        // DualPivotQuicksort
//        Arrays.sort(integers);
//        System.out.println(Arrays.toString(integers));

        sortObj();
    }

    /**
     * 没有内置方法给基本类型数组排序
     */
    private static void sortObj() {
        var A = new Melon("a", 1);
        var B = new Melon("b", 2);
        var C = new Melon("c", 0);
        var D = new Melon("d", 9);
        Melon[] melons = new Melon[]{A, B, C, D};

        // DEFAULT
//        Arrays.sort(melons, new Comparator<Melon>() {
//            @Override
//            public int compare(Melon o1, Melon o2) {
//                return Integer.compare(o1.getWeight(), o2.getWeight());
//            }
//        });

        // LAMBDA
//        Arrays.sort(melons, (Melon o1, Melon o2) -> Integer.compare(o1.getWeight(), o2.getWeight()));
//        Arrays.sort(melons, Comparator.comparingInt(Melon::getWeight));

        // PARALLEL for large arrays
//        Arrays.parallelSort(melons, Comparator.comparingInt(Melon::getWeight));

        // REVERSE
        Arrays.sort(melons, Collections.reverseOrder(Comparator.comparingInt(Melon::getWeight)));

        System.out.println(Arrays.toString(melons));
    }
}

class Melon {
    private final String type;
    private final int weight;

    public Melon(String type, int weight) {
        this.type = type;
        this.weight = weight;
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
}