package com.lhf.designpatternlib.compound;

/**
 * 鹅适配器
 * Created by Joshua on 2021/2/21 0:34
 */
public class GooseAdapter implements Quackable {

    Goose goose;
    Observable observable;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
        observable = new Observable(this);
    }

    @Override
    public void quack() {
        goose.honk();
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
