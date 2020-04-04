package com.lhf.designpatternlib.decorator;

/**
 * Created by Joshua on 2020/4/4.
 */
public class ConcreteDecorator1 extends Decorator {

    public ConcreteDecorator1(IComponent component) {
        super(component);
    }

    @Override
    public void operation() {
        System.out.println("ConcreteDecorator1.operation() called");
        super.operation();
        addedFunction();
    }

    public void addedFunction() {
        System.out.println("ConcreteDecorator1.addedFunction() called");
    }
}
