package com.lhf.designpatternlib.strategy.example;

/**
 * 绿头鸭
 * Created by Joshua on 2021/1/3 22:52
 */
public class MallardDuck extends Duck {
    public MallardDuck() {
        flyBehavior = new FlyWithWings();
        quackBehavior = new Quack();
    }

    @Override
    public void display() {
        System.out.println("这是一只---------->绿头鸭");
    }
}
