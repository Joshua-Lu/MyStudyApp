package com.lhf.designpatternlib.compound;

import java.util.ArrayList;

/**
 * Created by Joshua on 2021/2/21 1:23
 */
public class Observable implements QuackObservable {

    ArrayList<QuackObserver> observers = new ArrayList<>();
    Quackable duck;

    public Observable(Quackable duck) {
        this.duck = duck;
    }

    @Override
    public void registerObserver(QuackObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (QuackObserver observer : observers) {
            observer.update(duck);
        }
    }
}
