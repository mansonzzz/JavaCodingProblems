package com.m.jcp.chapter_8.execute_around_pattern;

import java.util.Scanner;

/**
 * @author zhangtian1
 */
public class ExecuteAroundPattern {
    public static void main(String[] args) throws Exception {
        double singleDouble = Doubles.read(ExecuteAroundPattern::getFirst);
        System.out.println("Single double: " + singleDouble);
    }

    private static double getFirst(Scanner scanner) {
        if (scanner.hasNextDouble()) {
            return scanner.nextDouble();
        }
        return Double.NaN;
    }
}
