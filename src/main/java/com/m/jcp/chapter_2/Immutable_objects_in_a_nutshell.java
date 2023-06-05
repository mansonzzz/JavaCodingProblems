package com.m.jcp.chapter_2;

import java.lang.reflect.Field;

/**
 * @author zhangtian1
 */
public class Immutable_objects_in_a_nutshell {
}

class StringClazz {
    public static void main(String[] args) throws Exception {
        jdk17();
    }

    private static void jdk8() throws Exception {
        String s = "Hello World";
        System.out.println("s = " + s);

        Field valueFieldOfString = String.class.getDeclaredField("value");
        valueFieldOfString.setAccessible(true);

        char[] value = (char[]) valueFieldOfString.get(s);
        value[5] = '_';
        System.out.println("s = " + s);
    }

    /**
     * 由于在JDK17中，String类的实现已经从char[]变成了byte[]，所以在JDK9及以后的版本中，需要使用byte[]来修改字符串
     * JDK9以上模块不能使用反射去访问非公有的成员/成员方法以及构造方法，除非模块标识为opens去允许反射访问。
     */
    private static void jdk17() throws Exception {
        String s = "Hello World";
        System.out.println("s = " + s);

        Field valueFieldOfString = String.class.getDeclaredField("value");
        // 需要使用`--add-opens java.base/java.lang=ALL-UNNAMED`来开放模块
        valueFieldOfString.setAccessible(true);

        byte[] value = (byte[]) valueFieldOfString.get(s);
        value[5] = '_';
        System.out.println("s = " + s);
    }
}

final class ImmutableClazz {
    private final double x;
    private final double y;

    public ImmutableClazz(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
