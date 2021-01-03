package com.lhf.designpatternlib.strategy.example;

/**
 * Created by Joshua on 2021/1/3 22:58
 */
public class Squeak implements QuackBehavior {
    @Override
    public void quack() {
        System.out.println("橡皮鸭子吱吱叫");
    }
}
