package com.m.jcp.chapter_8.writing_functional_interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author zhangtian1
 */
public final class FiltersV2 {
    private FiltersV2() {
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {

        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }

        List<T> result = new ArrayList<>();
        for (T t : list) {
            if (t != null && predicate.test(t)) {
                result.add(t);
            }
        }

        return result;
    }
}
