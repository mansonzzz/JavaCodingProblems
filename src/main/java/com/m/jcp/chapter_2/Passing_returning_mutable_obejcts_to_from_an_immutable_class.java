package com.m.jcp.chapter_2;

/**
 * @author zhangtian1
 */
public class Passing_returning_mutable_obejcts_to_from_an_immutable_class {
    public static void main(String[] args) {
        Radius r = new Radius(0, 120);
        Point point = new Point(1.23, 4.12, r);
        System.out.println(point.getRadius());
        r.setStart(5);
        System.out.println(point.getRadius());
        point.getRadius().setStart(5);
        System.out.println(point.getRadius());
    }
}

class Radius {
    private int start;
    private int end;

    public Radius(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Radius{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

final class Point {
    private final double x;
    private final double y;
    private final Radius radius;

    public Point(double x, double y, Radius radius) {
        this.x = x;
        this.y = y;
        // this.radius = radius;
        // 克隆对象，使其不可变
        this.radius = new Radius(radius.getStart(), radius.getEnd());
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Radius getRadius() {
        // return radius;
        return new Radius(this.radius.getStart(), this.radius.getEnd());
    }
}
