package com.m.jcp.chapter_9.method_reference;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

/**
 * @author zhangtian1
 */
public class Method_reference_to_static_methods {
    public static void main(String[] args) {
        List<Melon> melons = Arrays.asList(
                new Melon("Crenshaw", 1200), new Melon("Gac", 3000),
                new Melon("Hemi", 2600), new Melon("Hemi", 1600));

        Map<String, List<Melon>> result = melons.stream().map(Melon::growingWeight).collect(groupingBy(Melon::type));
        assert result.get("Crenshaw").size() == 1;
        assert result.get("Crenshaw").get(0).weight() == 2200;
        assert result.get("Gac").size() == 1;
        assert result.get("Gac").get(0).weight() == 4000;

        assert result.get("Hemi").size() == 2;
    }
}
