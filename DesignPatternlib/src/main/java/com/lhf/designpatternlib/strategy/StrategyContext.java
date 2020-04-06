package com.lhf.designpatternlib.strategy;

/**
 * 策略模式
 * 该模式定义了一系列算法，并将每个算法封装起来，使它们可以相互替换，且算法的变化不会影响使用算法的客户。
 * Created by Joshua on 2020/4/6 17:14.
 */
public class StrategyContext {
    private IStrategy strategy;

    public void setStrategy(IStrategy strategy) {
        this.strategy = strategy;
    }

    public StrategyContext(IStrategy strategy) {
        this.strategy = strategy;
    }

    public void strategyMethod() {
        strategy.strategyMethod();
    }
}
