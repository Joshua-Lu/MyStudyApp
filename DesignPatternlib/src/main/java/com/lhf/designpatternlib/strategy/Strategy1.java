package com.lhf.designpatternlib.strategy;

/**
 * Created by Joshua on 2020/4/6 17:13.
 */
public class Strategy1 implements IStrategy {
    @Override
    public void strategyMethod() {
        System.out.println("Strategy1.strategyMethod() called");
    }
}
