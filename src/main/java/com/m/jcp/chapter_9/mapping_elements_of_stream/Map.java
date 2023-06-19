package com.m.jcp.chapter_9.mapping_elements_of_stream;

import com.m.jcp.chapter_7.getting_the_annotation_of_a_receiver_type.Melon;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhangtian1
 */
public class Map {
    public static void main(String[] args) {
        List<Melon> melons = Arrays.asList(new Melon("Gac", 2000),
                new Melon("Hemi", 1600), new Melon("Gac", 3000),
                new Melon("Apollo", 2000), new Melon("Horned", 1700));
        // [Gac, Hemi, Gac, Apollo, Horned]
        System.out.println(melons.stream().map(Melon::getType).toList());
        System.out.println(">>>>> <<<<<");

        // [Melon{type='Gac', weight=2500}, Melon{type='Hemi', weight=2100}, Melon{type='Gac', weight=3500}, Melon{type='Apollo', weight=2500}, Melon{type='Horned', weight=2200}]
        System.out.println(melons.stream().map(m -> new Melon(m.getType(), m.getWeight() + 500)).toList());
    }
}
