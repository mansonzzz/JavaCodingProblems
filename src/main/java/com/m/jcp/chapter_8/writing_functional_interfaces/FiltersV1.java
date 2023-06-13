package com.m.jcp.chapter_8.writing_functional_interfaces;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtian1
 */
public final class FiltersV1 {
    private FiltersV1() {
    }

    public static List<Melon> filter(List<Melon> list, MelonPredicate predicate) {

        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }

        List<Melon> result = new ArrayList<>();
        for (Melon melon : list) {
            if (melon != null && predicate.test(melon)) {
                result.add(melon);
            }
        }

        return result;
    }
}
