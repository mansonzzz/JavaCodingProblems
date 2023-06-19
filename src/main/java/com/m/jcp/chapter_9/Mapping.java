package com.m.jcp.chapter_9;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;

/**
 * @author zhangtian1
 */
public class Mapping {
    public static void main(String[] args) {
        List<IdentityDTO> identities = List.of(new IdentityDTO("1", "A"), new IdentityDTO("2", "B"), new IdentityDTO("3", "C"));
        Map<String, List<String>> collect = identities.stream().collect(
                groupingBy(IdentityDTO::getType, mapping(IdentityDTO::getId, toList()))
        );
        System.out.println(collect);
    }
}
