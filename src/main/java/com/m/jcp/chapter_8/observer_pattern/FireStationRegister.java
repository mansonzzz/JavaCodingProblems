package com.m.jcp.chapter_8.observer_pattern;

/**
 * @author zhangtian1
 */
public interface FireStationRegister {
    void registerFireObserver(FireObserver fireObserver);

    void notifyFireObserver(String address);
}
