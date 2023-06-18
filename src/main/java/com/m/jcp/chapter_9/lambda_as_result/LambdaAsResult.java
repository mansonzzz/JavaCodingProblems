package com.m.jcp.chapter_9.lambda_as_result;

import java.util.List;
import java.util.function.Function;

/**
 * @author zhangtian1
 */
public final class LambdaAsResult {
    private LambdaAsResult() {
    }

    public static Function<String, String> execute(List<Function<String, String>> functions) {
        return functions.stream().reduce(Function.identity(), Function::andThen);
    }
}
