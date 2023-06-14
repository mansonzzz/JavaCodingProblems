package com.m.jcp.chapter_8.execute_around_pattern;

import java.util.Scanner;

/**
 * @author zhangtian1
 */
public final class Doubles {
    private Doubles() {
    }

    public static double read(ScannerDoubleFunction func) throws Exception {
        if (func == null) {
            throw new IllegalArgumentException("func must not be null");
        }
        try (Scanner scanner = new Scanner(System.in)) {
            return func.readDouble(scanner);
        }
    }
}
