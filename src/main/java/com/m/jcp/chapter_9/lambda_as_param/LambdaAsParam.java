package com.m.jcp.chapter_9.lambda_as_param;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtian1
 */
public final class LambdaAsParam {

    private LambdaAsParam() {
    }

    public static List<String> replace(List<String> list, Replacer<String> replacer) {
        List<String> result = new ArrayList<>();
        list.forEach(s -> result.add(replacer.replace(s)));
        return result;
    }
}
