package com.lhf.designpatternlib.compound;

/**
 * 橡皮鸭
 * <p>
 * Created by Joshua on 2021/2/21 0:19
 */
public class RubberDuck implements Quackable {

    Observable observable;

    public RubberDuck() {
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("RubberDuck.quack() called: 橡皮鸭叫 Squeak");
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
