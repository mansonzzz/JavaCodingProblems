package com.m.jcp.chapter_7.getting_the_annotation_of_a_receiver_type;

import java.lang.reflect.AnnotatedType;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author zhangtian1
 * 通过反射获取显式接收器参数上的注解，以及获取注解类型、注解的接口和注解的所有者类型。
 */
public class Getting_the_annotation_of_a_receiver_type {
    public static void main(String[] args) throws Exception {
        Class<Melon> clazz = Melon.class;
        Method eat = clazz.getDeclaredMethod("eat");

        AnnotatedType annotatedReceiverType = eat.getAnnotatedReceiverType();

        System.out.println(">>> type: " + annotatedReceiverType.getType());
        // >>> annotations: [@com.m.jcp.chapter_7.getting_the_annotation_of_a_receiver_type.Ripe()]
        System.out.println(">>> annotations: " + Arrays.toString(annotatedReceiverType.getAnnotations()));
        System.out.println(">>> annotated owner type:" + annotatedReceiverType.getAnnotatedOwnerType());
    }
}
