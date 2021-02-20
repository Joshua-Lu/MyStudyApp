package com.lhf.designpatternlib.compound;

/**
 * 红头鸭
 * <p>
 * Created by Joshua on 2021/2/21 0:19
 */
public class RedheadDuck implements Quackable {

    // 通过组合方式，实现Observable
    Observable observable;

    public RedheadDuck() {
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("RedheadDuck.quack() called: 红头鸭叫 Quack");
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
