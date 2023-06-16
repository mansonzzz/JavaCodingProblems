package com.m.jcp.chapter_8.factory_pattern;

@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
}