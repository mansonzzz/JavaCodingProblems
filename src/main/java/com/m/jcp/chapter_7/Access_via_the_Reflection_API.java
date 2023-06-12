package com.m.jcp.chapter_7;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author zhangtian1
 * <p>
 * Java 11 中引入了两个新的属性：一个叫做 NestMembers 的属性，用于标识其它已知的静态 nest 成员；
 * <p>
 * 另外一个是每个 nest 成员都包含的 NestHost 属性，用于标识出它的 nest 宿主类。在编译期就映射了双方的寄宿关系，不再需要桥接了。
 */
public class Access_via_the_Reflection_API {
    public static void main(String[] args) throws Exception {
        Class<Melon> melonClass = Melon.class;
        Class<Melon.Inner> innerClass = Melon.Inner.class;
        assert melonClass.getNestHost() == innerClass;
        assert melonClass.getNestHost() == innerClass.getNestHost();
        System.out.println(Arrays.toString(melonClass.getNestMembers())); // [class com.m.jcp.chapter_7.Melon, class com.m.jcp.chapter_7.Melon$Inner]

        // 通过 java.beans.* 的API来获取属性描述符
        Melon.Inner inner = new Melon.Inner();
        PropertyDescriptor[] pds = Introspector.getBeanInfo(Melon.Inner.class).getPropertyDescriptors();
        for (PropertyDescriptor pd : pds) {
            if (pd.getReadMethod() != null && !"class".equals(pd.getName())) {
                System.out.println(pd.getReadMethod());
            }

            if (pd.getWriteMethod() != null && !"class".equals(pd.getName())) {
                System.out.println(pd.getWriteMethod());
                pd.getWriteMethod().invoke(inner, 123123);
            }
        }
        assert inner.getValue() == 123123;
    }

}

class Melon {

    static class Inner {
        private int value;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    public static void main(String... args) throws Exception {
        Field field = Inner.class.getDeclaredField("value");
        field.set(new Inner(), 123); // JDK8 抛出 IllegalAccessException
    }
}