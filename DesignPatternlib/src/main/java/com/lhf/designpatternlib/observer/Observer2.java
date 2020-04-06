package com.lhf.designpatternlib.observer;

/**
 * Created by Joshua on 2020/4/6 20:54.
 */
public class Observer2 implements IObserver {
    @Override
    public void response() {
        System.out.println("Observer2.response() called");
    }
}
