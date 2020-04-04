package com.lhf.designpatternlib.bridge;

/**
 * 桥接模式
 * 将抽象与实现分离，使它们可以独立变化。它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度。
 * Created by Joshua on 2020/4/4.
 */
public abstract class Bag {
    IColor color;

    public void setColor(IColor color) {
        this.color = color;
    }

    public abstract void show();
}
