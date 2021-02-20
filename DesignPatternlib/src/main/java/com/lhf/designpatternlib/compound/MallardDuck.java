package com.lhf.designpatternlib.compound;

/**
 * 绿头鸭
 * <p>
 * Created by Joshua on 2021/2/21 0:19
 */
public class MallardDuck implements Quackable {

    Observable observable;

    public MallardDuck() {
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        System.out.println("MallardDuck.quack() called: 绿头鸭叫 Quack");
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
