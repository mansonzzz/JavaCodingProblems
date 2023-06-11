package com.m.jcp.chapter_7;

import java.lang.reflect.Constructor;

/**
 * @author zhangtian1
 * 通过反射来实例化对象
 */
public class Instantiating_via_a_reflected_constructor {
    public static void main(String[] args) throws Exception {
        Class<Car> clazz = Car.class;

        Constructor<Car> emptyCnstr = clazz.getConstructor();
        Constructor<Car> idNameCnstr = clazz.getConstructor(int.class, String.class);
        Constructor<Car> idColorCnstr
                = clazz.getConstructor(int.class, Car.Color.class);
        Constructor<Car> idNameColorCnstr
                = clazz.getConstructor(int.class, String.class, Car.Color.class);

        Car test = idNameCnstr.newInstance(1, "test");
        System.out.println(test);
    }

    static class Car {

        private int id;
        private String name;
        private Color color;

        public Car() {
        }

        public Car(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public Car(int id, Color color) {
            this.id = id;
            this.color = color;
        }

        public Car(int id, String name, Color color) {
            this.id = id;
            this.name = name;
            this.color = color;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", color=" + color +
                    '}';
        }

        static class Color {
            private int id;
            private String name;

            public Color() {
            }

            public Color(int id, String name) {
                this.id = id;
                this.name = name;
            }
        }
    }
}
