package com.m.jcp.chapter_9.mapping_elements_of_stream;

import com.m.jcp.chapter_7.getting_the_annotation_of_a_receiver_type.Melon;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author zhangtian1
 */
public class FlatMap {
    public static void main(String[] args) {
        Melon[][] melonsArray = {
                {new Melon("Gac", 2000), new Melon("Hemi", 1600)},
                {new Melon("Gac", 2000), new Melon("Apollo", 2000)},
                {new Melon("Horned", 1700), new Melon("Hemi", 1600)}
        };
        // V1
        Stream<Melon[]> stream = Arrays.stream(melonsArray);
        // 在这里distinct不会寻找一个独立的melon，而是寻找一个独立的melon数组
        System.out.println(stream.distinct().toList());

        // V2 flatMap 用于将多个stream合并成一个stream
        Stream<Melon> melonStream = Arrays.stream(melonsArray)
                .flatMap(Arrays::stream);
        List<Melon> list = melonStream.distinct().toList();
        System.out.println(list);
    }
}
