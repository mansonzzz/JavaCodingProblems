package com.m.jcp.chapter_8.execute_around_pattern;

import java.util.Scanner;

/**
 * @author zhangtian1
 */
@FunctionalInterface
public interface ScannerDoubleFunction {

    double readDouble(Scanner scanner) throws Exception;

}
