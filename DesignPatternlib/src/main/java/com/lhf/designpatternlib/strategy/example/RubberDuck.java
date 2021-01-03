package com.lhf.designpatternlib.strategy.example;

/**
 * 橡皮鸭
 * Created by Joshua on 2021/1/3 22:52
 */
public class RubberDuck extends Duck {
    public RubberDuck() {
        flyBehavior = new FlyNoWay();
        quackBehavior = new Squeak();
    }

    @Override
    public void display() {
        System.out.println("这是一只---------->橡皮鸭");
    }
}
