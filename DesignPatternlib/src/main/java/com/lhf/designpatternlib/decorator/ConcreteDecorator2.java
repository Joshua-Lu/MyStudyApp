package com.lhf.designpatternlib.decorator;

/**
 * Created by Joshua on 2020/4/4.
 */
public class ConcreteDecorator2 extends Decorator {

    public ConcreteDecorator2(IComponent component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("ConcreteDecorator2.operation() called");
        super.operation();
        addedFunction();
    }

    public void addedFunction() {
        System.out.println("ConcreteDecorator2.addedFunction() called");
    }
}
