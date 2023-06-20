package com.m.jcp.chapter_9.writing_custom_collector;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static java.util.stream.Collector.Characteristics.CONCURRENT;
import static java.util.stream.Collector.Characteristics.IDENTITY_FINISH;

/**
 * @author zhangtian1
 */
public class MelonCollector implements Collector<Melon, Map<Boolean, List<Melon>>, Map<Boolean, List<Melon>>> {
    @Override
    public Supplier<Map<Boolean, List<Melon>>> supplier() {
        return () -> new HashMap<>() {
            {
                put(true, new ArrayList<>());
                put(false, new ArrayList<>());
            }
        };
    }

    @Override
    public BiConsumer<Map<Boolean, List<Melon>>, Melon> accumulator() {
        return (acc, melon) -> acc.get(melon.weight() > 2000).add(melon);
    }

    @Override
    public BinaryOperator<Map<Boolean, List<Melon>>> combiner() {
        return (map, addMap) -> {
            System.out.println(">>> map:" + map + "->" + Thread.currentThread().getName());
            System.out.println(">>> addMap:" + addMap + "->" + Thread.currentThread().getName());
            map.get(true).addAll(addMap.get(true));
            map.get(false).addAll(addMap.get(false));

            return map;
        };
    }

    @Override
    public Function<Map<Boolean, List<Melon>>, Map<Boolean, List<Melon>>> finisher() {
        return Function.identity();
    }

    @Override
    public Set<Characteristics> characteristics() {
        // IDENTIFY_FINISH: finisher() 方法返回的是一个恒等函数
        // CONCURRENT: combiner() 方法支持并行
        return Set.of(IDENTITY_FINISH, CONCURRENT);
    }
}
