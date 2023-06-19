package com.m.jcp.chapter_9.debugging_lambda;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhangtian1
 */
public class DebuggingLambda {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("anna", "bob", null, "mary");
//        debuggingLambdaV1(names);
        debuggingLambdaV2(names);
    }

    /**
     * OLD WAY
     * at com.m.jcp.chapter_9.debugging_lambda.DebuggingLambda.lambda$debuggingLambdaV1$0(DebuggingLambda.java:17)
     * 报错不友好
     */
    public static void debuggingLambdaV1(List<String> names) {
        names.stream()
                .filter(name -> name.startsWith("a"))
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }

    /**
     * RECOMMENDED WAY
     * 将peek放在filter和map之间，可以看到每个操作的结果
     * <p>
     * >>> stream: anna
     * <p>
     * >>> filter: anna
     * <p>
     * >>> map: ANNA
     * <p>
     * ANNA
     * <p>
     * >>> stream: bob
     * <p>
     * >>> stream: null
     */
    public static void debuggingLambdaV2(List<String> names) {
        names.stream()
                .peek(name -> System.out.println(">>> stream: " + name))
                .filter(name -> name.startsWith("a"))
                .peek(name -> System.out.println(">>> filter: " + name))
                .map(String::toUpperCase)
                .peek(name -> System.out.println(">>> map: " + name))
                .forEach(System.out::println);
    }
}
