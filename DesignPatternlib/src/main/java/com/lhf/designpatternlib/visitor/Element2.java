package com.lhf.designpatternlib.visitor;

/**
 * Created by Joshua on 2020/4/6 22:47.
 */
public class Element2 implements IElement {

    @Override
    public void accept(IVisitor visitor) {
        System.out.println("Element2.accept() called with: visitor = [" + visitor + "]");
        visitor.visit(this);
    }

    public void operation() {
        System.out.println("Element2.operation() called");
    }
}
