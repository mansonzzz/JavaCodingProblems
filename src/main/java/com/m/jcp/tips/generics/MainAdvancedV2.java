package com.m.jcp.tips.generics;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtian1
 */
public class MainAdvancedV2 {
    public static void main(String[] args) {

    }

    /**
     * 在 T/R 明确的情况下，应该放宽入参和返回类型，使方法更灵活
     */
    private static <T, R> List<R> map(List<T> src, Mapper<? super T, ? extends R> mapper) {
        List<R> dst = new ArrayList<>();
        for (T t : src) {
            dst.add(mapper.map(t));
        }
        return dst;
    }

    private static interface Mapper<T, R> {
        R map(T t);
    }
}
