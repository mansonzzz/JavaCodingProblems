package com.m.jcp.chapter_9;

import com.m.jcp.chapter_9.lambda_as_param.LambdaAsParam;
import com.m.jcp.chapter_9.lambda_as_result.LambdaAsResult;
import com.m.jcp.chapter_9.test_lambda_method.TestLambdaMethod;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author zhangtian1
 */
public class Chapter9Test {

    @Test
    public void test001LambdaAsParam() {
        var list = List.of("Ann a 15", "Mir el 28", "D oru 33");

        var resultOfReplaceWhiteSpace = LambdaAsParam.replace(list, s -> s.replaceAll("\\s", ""));
        assertEquals(resultOfReplaceWhiteSpace, List.of("Anna15", "Mirel28", "Doru33"));

        var resultOfReplaceR = LambdaAsParam.replace(list, s -> s.replaceAll("r", "E"));
        assertEquals(resultOfReplaceR, List.of("Ann a 15", "MiE el 28", "D oEu 33"));
    }

    @Test
    public void test002LambdaAsResult() {
        var FUNC = LambdaAsResult.execute(List.of(String::toUpperCase, s -> s + "!!!"));
        assertEquals(FUNC.apply("hello"), "HELLO!!!");
    }

    @Test
    public void test003TestLambdaMethod() {
        var p1 = "hello";
        var p2 = "Some";
        assertEquals("ho", TestLambdaMethod.findFirstAndLastCharV1(p1));
        assertEquals(1, TestLambdaMethod.extractCharacter(p2).length());
    }

}
