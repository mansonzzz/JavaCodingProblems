package com.m.jcp.chapter_8.observer_pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangtian1
 */
public class FireStation implements FireStationRegister {

    private final List<FireObserver> fireObservers = new ArrayList<>();

    @Override
    public void registerFireObserver(FireObserver fireObserver) {
        if (fireObserver == null) {
            throw new NullPointerException("fireObserver is null");
        }
        fireObservers.add(fireObserver);
    }

    @Override
    public void notifyFireObserver(String address) {
        if (address == null) {
            throw new NullPointerException("address is null");
        }
        fireObservers.forEach(fireObserver -> fireObserver.fire(address));
    }
}
