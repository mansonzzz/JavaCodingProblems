package com.m.jcp.chapter_8.loan_pattern;

/**
 * @author zhangtian1
 */
public class Main {
    public static void main(String[] args) throws Exception {
        double result1 = Formula.compute((sc) -> sc.add().add().minus().result());
        System.out.println(result1);
    }
}
