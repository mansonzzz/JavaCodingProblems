package com.m.jcp.chapter_5;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangtian1
 */
public class Test {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("postgresql", "9.6.1 ");
        map.put("mysql", "5.1 5.2 5.6 ");
        map.merge("mysql", "8.0", String::concat);
        System.out.println(map);
    }
}
