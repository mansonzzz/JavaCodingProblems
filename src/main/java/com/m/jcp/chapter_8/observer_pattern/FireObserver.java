package com.m.jcp.chapter_8.observer_pattern;

/**
 * @author zhangtian1
 * 所有观察者都通过这个方法来接收通知
 */
@FunctionalInterface
public interface FireObserver {
    void fire(String address);
}
