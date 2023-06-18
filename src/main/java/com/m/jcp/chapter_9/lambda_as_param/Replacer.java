package com.m.jcp.chapter_9.lambda_as_param;

/**
 * @author zhangtian1
 */
@FunctionalInterface
public interface Replacer<String> {
    String replace(String s);
}
