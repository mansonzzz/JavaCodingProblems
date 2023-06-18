package com.m.jcp.chapter_8.command_pattern;

/**
 * @author zhangtian1
 */
public class HardDisk implements IODevice {
    @Override
    public void copy() {
        System.out.println(">>> copying ...");
    }

    @Override
    public void delete() {
        System.out.println(">>> deleting ...");
    }

    @Override
    public void move() {
        System.out.println(">>> moving ...");
    }
}
