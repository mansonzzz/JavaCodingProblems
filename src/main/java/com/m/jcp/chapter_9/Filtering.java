package com.m.jcp.chapter_9;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import static java.util.stream.Collectors.*;

/**
 * @author zhangtian1
 */
public class Filtering {
    public static void main(String[] args) {
        List<IdentityDTO> identities = List.of(new IdentityDTO("1", "A"), new IdentityDTO("2", "B"), new IdentityDTO("3", "C"));
        // 在groupingBy中使用filtering
        // filtering会为每种类型都创建一个set
        Map<String, Set<IdentityDTO>> resultOfGroupingByAndFiltering = identities.stream()
                .collect(groupingBy(IdentityDTO::getType, filtering(i -> !Objects.equals(i.getId(), "2"), toSet())));
        assert resultOfGroupingByAndFiltering.size() == 3;
        assert resultOfGroupingByAndFiltering.get("A").size() == 1;
        assert resultOfGroupingByAndFiltering.get("B").size() == 0;
        assert resultOfGroupingByAndFiltering.get("C").size() == 1;

        // 等同于
        Map<String, Set<IdentityDTO>> resultOfFilter = identities.stream()
                .filter(i -> !Objects.equals(i.getId(), "2"))
                .collect(groupingBy(IdentityDTO::getType, toSet()));
        assert resultOfFilter.size() == 2;
        assert resultOfFilter.get("A").size() == 1;
        assert resultOfFilter.get("C").size() == 1;
    }
}
