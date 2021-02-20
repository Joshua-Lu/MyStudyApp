package com.lhf.designpatternlib.compound;

import java.util.ArrayList;

/**
 * 组合模式
 * 鸭子集合
 * Created by Joshua on 2021/2/21 0:58
 */
public class Flock implements Quackable {

    ArrayList<Quackable> quackables = new ArrayList<>();

    public void add(Quackable quackable) {
        quackables.add(quackable);
    }

    @Override
    public void quack() {
        for (Quackable quackable : quackables) {
            quackable.quack();
        }
    }

    @Override
    public void registerObserver(QuackObserver observer) {
        for (Quackable quackable : quackables) {
            quackable.registerObserver(observer);
        }
    }

    @Override
    public void notifyObservers() {
        for (Quackable quackable : quackables) {
            quackable.notifyObservers();
        }
    }
}
