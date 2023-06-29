package com.m.jcp.tips.generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhangtian1
 * copy
 */
public class MainAdvancedV1 {
    public static void main(String[] args) {
        List<Integer> src = Arrays.asList(1, 2, 3, 4, 5);
        List<Number> dst = new ArrayList<>();

        // Integer 是 Number 的子类，可以直接向上转型存储进来
        copy1(src, dst);
        copy2(src, dst);
        copy3(src, dst);
        List<String> strDst = new ArrayList<>();
        // copy3(src, strDst); // 编译期就会报错
    }

    private static void copy1(List<Integer> src, List<Number> dst) {
        for (Integer obj : src) {
            dst.add(obj);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T, R> void copy2(List<T> src, List<R> dst) {
        for (T obj : src) {
            // 失去了编译期约束
            dst.add((R) obj);
        }
    }

    /**
     * 使用 T 作为继承层次中的中间类型
     * <p>
     * <ul>
     *     <li>src 中的元素必须是 T 的子类</li>
     *     <li>dst 中的元素必须是 T 的父类</li>
     * </ul>
     * <? extends T> 作为 T 的子类，可以向上转型为 <? super T> T 的父类。此时无须关心 T 到底是什么类型。
     */
    private static <T> void copy3(List<? extends T> src, List<? super T> dst) {
        for (T t : src) {
            /*
                支持了将 List<Double> 拷贝给 List<Number>
                <p>
                同时在编译期就规避了类型转换异常
             */
            dst.add(t);
        }
    }
}