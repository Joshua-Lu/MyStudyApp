package com.lhf.designpatternlib.compound;

/**
 * 不带装饰者的鸭子生产工厂
 * <p>
 * Created by Joshua on 2021/2/21 0:48
 */
public class DuckFactory extends AbstractDuckFactory {
    @Override
    Quackable createMallardDuck() {
        return new MallardDuck();
    }

    @Override
    Quackable createRedheadDuck() {
        return new RedheadDuck();
    }

    @Override
    Quackable createDuckCall() {
        return new DuckCall();
    }

    @Override
    Quackable createRubberDuck() {
        return new RubberDuck();
    }

    @Override
    Quackable createGooseDuck() {
        return new GooseAdapter(new Goose());
    }
}
