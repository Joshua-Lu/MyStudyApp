package com.lhf.designpatternlib.strategy.example;

/**
 * Created by Joshua on 2021/1/3 22:56
 */
public class FlyWithWings implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("用翅膀飞行");
    }
}
