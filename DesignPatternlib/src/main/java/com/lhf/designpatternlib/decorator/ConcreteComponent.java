package com.lhf.designpatternlib.decorator;

/**
 * Created by Joshua on 2020/4/4.
 */
public class ConcreteComponent implements IComponent {
    @Override
    public void operation() {
        System.out.println("ConcreteComponent.operation() called");
    }
}
