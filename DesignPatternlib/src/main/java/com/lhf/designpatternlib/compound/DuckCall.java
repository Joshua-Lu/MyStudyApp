package com.lhf.designpatternlib.compound;

/**
 * 鸭鸣器
 * <p>
 * Created by Joshua on 2021/2/21 0:19
 */
public class DuckCall implements Quackable {

    Observable observable;

    public DuckCall() {
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("DuckCall.quack() called: 鸭鸣器叫 Kwak");
        notifyObservers();
    }

    @Override
    public void registerObserver(QuackObserver observer) {
        observable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        observable.notifyObservers();
    }
}
