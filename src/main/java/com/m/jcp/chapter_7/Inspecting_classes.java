package com.m.jcp.chapter_7;

import org.apache.commons.lang3.tuple.Pair;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 * @author zhangtian1
 */
public class Inspecting_classes {
    public static void main(String[] args) {
        Pair<String, String> pair = Pair.of("a", "b");

        Class<?> clazz = pair.getClass();
        // >>> clazz.getName(): org.apache.commons.lang3.tuple.ImmutablePair
        System.out.println(">>> clazz.getName(): " + clazz.getName());
        // >>> clazz.getSimpleName(): ImmutablePair
        System.out.println(">>> clazz.getSimpleName(): " + clazz.getSimpleName());
        // >>> clazz.getCanonicalName(): org.apache.commons.lang3.tuple.ImmutablePair
        System.out.println(">>> clazz.getCanonicalName(): " + clazz.getCanonicalName());

        // --------------------------------------------------
        int modifiers = clazz.getModifiers();
        System.out.println(">>> isPublic: " + Modifier.isPublic(modifiers)); // true
        System.out.println(">>> isFinal: " + Modifier.isFinal(modifiers)); // true
        System.out.println(">>> isAbstract: " + Modifier.isAbstract(modifiers)); // false

        // --------------------------------------------------
        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println(">>> Interfaces: " + Arrays.toString(interfaces));

        // --------------------------------------------------
        Constructor<?>[] constructors = clazz.getConstructors();
        // >>> Constructors: [public org.apache.commons.lang3.tuple.ImmutablePair(java.lang.Object,java.lang.Object)]
        System.out.println(">>> Constructors: " + Arrays.toString(constructors));

        // --------------------------------------------------
        Field[] declaredFields = clazz.getDeclaredFields();
        // 类的所有成员变量，包括私有的
        System.out.println(">>> Fields: " + Arrays.toString(declaredFields));

        Module module = clazz.getModule();
        System.out.println(">>> module: " + module);

        // --------------------------------------------------
        // Lorg/apache/commons/lang3/tuple/ImmutablePair;
        System.out.println(">>> type descriptor: " + clazz.descriptorString());
    }
}
