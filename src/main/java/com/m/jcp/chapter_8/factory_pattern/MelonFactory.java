package com.m.jcp.chapter_8.factory_pattern;

import java.util.Map;
import java.util.function.Supplier;

/**
 * @author zhangtian1
 */
public class MelonFactory {

    private static final TriFunction<String, Integer, String, Melon> MELON = Melon::new;
    private static final Map<String, Supplier<Fruit>> MELONS = Map.of("Gac", Gac::new, "Hemi", Hemi::new);

    public static Fruit newInstance(Class<?> clazz) {
        Supplier<Fruit> supplier = MELONS.get(clazz.getSimpleName());
        if (supplier == null) {
            throw new IllegalArgumentException("No such melon: " + clazz.getSimpleName());
        }
        return supplier.get();
    }

    public static Fruit newInstance(String type, int weight, String color) {
        return MELON.apply(type, weight, color);
    }
}
