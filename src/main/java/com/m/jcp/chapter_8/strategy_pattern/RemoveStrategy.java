package com.m.jcp.chapter_8.strategy_pattern;

/**
 * @author zhangtian1
 */
@FunctionalInterface
public interface RemoveStrategy {
    String execute(String s);
}
