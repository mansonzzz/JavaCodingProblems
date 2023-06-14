package com.m.jcp.chapter_7;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

/**
 * @author zhangtian1
 */
public class Getting_generic_types {

    private List<Melon> melons;

    public static void main(String[] args) throws Exception {
        Type type = Getting_generic_types.class.getDeclaredField("melons").getGenericType();
        // Class of type argument: class com.m.jcp.chapter_7.Melon
        // Simple name of type argument: Melon
        printGenerics(type);
    }

    public static void printGenerics(Type genericType) {
        if (genericType instanceof ParameterizedType type) {
            Type[] typeOfArguments = type.getActualTypeArguments();
            for (Type typeOfArgument : typeOfArguments) {
                Class<?> classTypeOfArgument = (Class<?>) typeOfArgument;
                System.out.println("Class of type argument: "
                                   + classTypeOfArgument);
                System.out.println("Simple name of type argument: "
                                   + classTypeOfArgument.getSimpleName());
            }
        }
    }
}

class Test {
    public void testMethod() throws IOException, SQLException {
    }

    public static void main(String[] args) {
        Method[] methods = Test.class.getMethods();
        for (Method method : methods) {
            if (method.getName().equals("testMethod")) {
                Type[] exceptionTypes = method.getGenericExceptionTypes();
                for (Type type : exceptionTypes) {
                    System.out.println(type.getTypeName());
                }
            }
        }
    }
}