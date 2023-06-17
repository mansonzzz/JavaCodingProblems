package com.m.jcp.chapter_8.observer_pattern;

/**
 * @author zhangtian1
 */
public class Main {
    public static void main(String[] args) {
        FireStation fireStation = new FireStation();
        fireStation.registerFireObserver((String address) -> {
            if (address.contains("nj")) {
                System.out.println(">>> nj fire station will go to this fire");
            }
        });
        fireStation.registerFireObserver((String address) -> {
            if (address.contains("sh")) {
                System.out.println(">>> sh fire station will go to this fire");
            }
        });
        fireStation.notifyFireObserver("nj");
    }
}
