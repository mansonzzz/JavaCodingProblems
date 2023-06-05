package com.m.jcp.chapter_2;

/**
 * @author zhangtian1
 * Cloneable接口不包含任何方法，它只是一个标记接口，用于指示实现它的类可以安全地进行字段复制。
 * TODO: 深拷贝
 */
public class Clone_objects {
    public static void main(String[] args) {
        Point_1 point_1 = new Point_1(1.0, 2.0, new Radius(3, 40));
        Point_1 point_2 = point_1.clone();
        System.out.println(point_1);
        System.out.println(point_2); // Point_1{x=1.0, y=2.0, radius=Radius{start=3, end=40}}
        // 默认的clone方法是浅拷贝，即只复制对象的引用，而不复制引用的对象
        // 所以，point_1和point_2的radius引用指向同一个对象
        point_1.getRadius().setStart(10);
        System.out.println(point_1); // Point_1{x=1.0, y=2.0, radius=Radius{start=10, end=40}}
        System.out.println(point_2); // Point_1{x=1.0, y=2.0, radius=Radius{start=10, end=40}}
    }
}

class Point_1 implements Cloneable {
    private double x;
    private double y;

    private Radius radius;

    public Point_1() {
    }

    public Point_1(double x, double y, Radius radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Point_1{" +
                "x=" + x +
                ", y=" + y +
                ", radius=" + radius +
                '}';
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Radius getRadius() {
        return radius;
    }

    public void setRadius(Radius radius) {
        this.radius = radius;
    }

    @Override
    public Point_1 clone() {
        try {
            return (Point_1) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
