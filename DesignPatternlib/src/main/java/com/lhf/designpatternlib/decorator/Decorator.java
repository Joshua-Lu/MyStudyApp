package com.lhf.designpatternlib.decorator;

/**
 * 装饰模式
 * 在不改变现有对象结构的情况下，动态地给该对象增加一些职责（即增加其额外功能）
 * Created by Joshua on 2020/4/4.
 */
public class Decorator implements IComponent {

    IComponent component;

    public Decorator(IComponent component) {
        this.component = component;
    }

    @Override
    public void operation() {
        component.operation();
    }
}
