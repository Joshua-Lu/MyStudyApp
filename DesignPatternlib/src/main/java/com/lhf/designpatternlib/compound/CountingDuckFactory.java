package com.lhf.designpatternlib.compound;

/**
 * 带装饰者的鸭子生产工厂
 * <p>
 * Created by Joshua on 2021/2/21 0:48
 */
public class CountingDuckFactory extends AbstractDuckFactory {
    @Override
    Quackable createMallardDuck() {
        return new QuackCounter(new MallardDuck());
    }

    @Override
    Quackable createRedheadDuck() {
        return new QuackCounter(new RedheadDuck());
    }

    @Override
    Quackable createDuckCall() {
        return new QuackCounter(new DuckCall());
    }

    @Override
    Quackable createRubberDuck() {
        return new QuackCounter(new RubberDuck());
    }

    @Override
    Quackable createGooseDuck() {
        return new QuackCounter(new GooseAdapter(new Goose()));
    }
}
