package com.lhf.designpatternlib.compound;

/**
 * Quack 装饰者
 * 增加计数功能
 * Created by Joshua on 2021/2/21 0:39
 */
public class QuackCounter implements Quackable {

    Quackable quackable;
    static int numberOfQuacks;// Quack 次数

    public QuackCounter(Quackable quackable) {
        this.quackable = quackable;
    }

    @Override
    public void quack() {
        quackable.quack();
        numberOfQuacks++;
    }

    public static int getNumberOfQuacks() {
        return numberOfQuacks;
    }

    @Override
    public void registerObserver(QuackObserver observer) {
        quackable.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        quackable.notifyObservers();
    }
}
