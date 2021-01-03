package com.lhf.designpatternlib.strategy.example;

/**
 * Created by Joshua on 2021/1/3 22:58
 */
public class MuteQuack implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("不会叫");
    }
}
