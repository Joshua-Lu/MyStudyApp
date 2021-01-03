package com.lhf.designpatternlib.strategy.example;

/**
 * Created by Joshua on 2021/1/3 22:56
 */
public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不能飞行");
    }
}
