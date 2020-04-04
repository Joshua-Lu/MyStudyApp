package com.lhf.designpatternlib.flyweight;

/**
 * Created by Joshua on 2020/4/4.
 */
public interface IFlyweight {
    void operation(UnsharedConcreteFlyweight state);
}
