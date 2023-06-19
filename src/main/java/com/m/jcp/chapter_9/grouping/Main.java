package com.m.jcp.chapter_9.grouping;

import com.m.jcp.chapter_7.getting_the_annotation_of_a_receiver_type.Melon;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

/**
 * @author zhangtian1
 */
public class Main {
    public static void main(String[] args) {
        List<Melon> melons = Arrays.asList(new Melon("Crenshaw", 2000),
                new Melon("Hemi", 1600), new Melon("Gac", 3000),
                new Melon("Apollo", 2000), new Melon("Horned", 1700),
                new Melon("Gac", 2000), new Melon("Cantaloupe", 2600)
        );

        // single-level grouping
        System.out.println(melons.stream().collect(groupingBy(Melon::getType)));
        System.out.println(">>>>> <<<<<");

        // downstream collector
        // {Crenshaw=2000.0, Apollo=2000.0, Gac=2500.0, Hemi=1600.0, Horned=1700.0, Cantaloupe=2600.0}
        System.out.println(melons.stream().collect(groupingBy(Melon::getType, averagingInt(Melon::getWeight))));
        System.out.println(">>>>> <<<<<");

        // max melon by type
        Map<String, Optional<Melon>> maxMelonByType = melons.stream()
                .collect(groupingBy(Melon::getType, maxBy(comparingInt(Melon::getWeight))));
        System.out.println(maxMelonByType);

        // multi-level grouping
        Map<String, Map<Integer, List<Melon>>> multiGroup = melons.stream()
                .collect(groupingBy(Melon::getType, groupingBy(Melon::getWeight, toList())));
        System.out.println(multiGroup);
    }
}
