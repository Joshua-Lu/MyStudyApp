package com.lhf.designpatternlib.compound;

/**
 * 生产鸭子的抽象工厂
 * <p>
 * Created by Joshua on 2021/2/21 0:46
 */
public abstract class AbstractDuckFactory {
    abstract Quackable createMallardDuck();

    abstract Quackable createRedheadDuck();

    abstract Quackable createDuckCall();

    abstract Quackable createRubberDuck();

    abstract Quackable createGooseDuck();
}
