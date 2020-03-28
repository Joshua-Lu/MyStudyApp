package com.lhf.designpatternlib.factory;

/**
 * Created by Joshua on 2020/3/29 1:26.
 */
public class AbstractFactory2 implements IAbstractFactory {
    @Override
    public IProductA createProductA() {
        return new ProductA2();
    }

    @Override
    public IProductB createProductB() {
        return new ProductB2();
    }
}
