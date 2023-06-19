package com.m.jcp.chapter_9;

import java.util.List;

import static java.util.stream.Collectors.*;

/**
 * @author zhangtian1
 */
public class FlatMapping {
    public static void main(String[] args) {
        List<IdentityDTO> identities1 = List.of(new IdentityDTO("1", "A"), new IdentityDTO("2", "B"));
        List<IdentityDTO> identities2 = List.of(new IdentityDTO("3", "C"), new IdentityDTO("4", "D"));
        List<List<IdentityDTO>> identities = List.of(identities1, identities2);
        identities.stream()
                .flatMap(List::stream)
                .collect(groupingBy(IdentityDTO::getType, mapping(IdentityDTO::getId, toList())))
                .forEach((k, v) -> System.out.println(k + ":" + v));
    }
}
