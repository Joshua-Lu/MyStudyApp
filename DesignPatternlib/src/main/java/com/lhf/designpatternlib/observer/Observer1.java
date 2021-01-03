package com.lhf.designpatternlib.observer;

/**
 * Created by Joshua on 2020/4/6 20:54.
 */
public class Observer1 implements IObserver {
    @Override
    public void response(String data) {
        System.out.println("Observer1.response() called with: data = [" + data + "]");
    }
}
