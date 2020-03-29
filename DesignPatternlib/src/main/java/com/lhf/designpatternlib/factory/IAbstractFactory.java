package com.lhf.designpatternlib.factory;

/**
 * 抽象工厂模式
 * 当只有一个接口，生产一类产品时，就是工厂方法模式
 * Created by Joshua on 2020/3/29 1:21.
 */
public interface IAbstractFactory {
    IProductA createProductA();
    IProductB createProductB();
}
