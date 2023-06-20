package com.m.jcp.chapter_9.writing_custom_collector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * @author zhangtian1
 * @see java.util.stream.Collector
 * T: 流中元素的类型
 * A: 累加器类型 (中间结果类型)
 * R: 返回结果类型
 */
public class Main {
    public static void main(String[] args) {
        List<Melon> melons = Arrays.asList(new Melon("Crenshaw", 1200),
                new Melon("Gac", 3000), new Melon("Hemi", 2600),
                new Melon("Hemi", 1600), new Melon("Gac", 1200),
                new Melon("Apollo", 2600), new Melon("Horned", 1700),
                new Melon("Gac", 3000), new Melon("Hemi", 2600)
        );

        Map<Boolean, List<Melon>> result = melons
                // .stream()
                // 使用并行流，combiner() 方法会被调用
                .parallelStream()
                .collect(new MelonCollector());
        assert result.get(true).size() == 4;
        assert result.get(false).size() == 5;

        System.out.println(">>> *** <<<");

        List<Object> numberList = Stream.of("ONE", "TWO", "THREE")
                .collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
        assert numberList.size() == 3;
        System.out.println(numberList);
    }
}
