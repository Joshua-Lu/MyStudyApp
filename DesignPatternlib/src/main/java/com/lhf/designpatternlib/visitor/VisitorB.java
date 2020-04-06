package com.lhf.designpatternlib.visitor;

/**
 * Created by Joshua on 2020/4/6 22:49.
 */
public class VisitorB implements IVisitor {
    @Override
    public void visit(Element1 element) {
        System.out.println("VisitorB.visit() called with: element = [" + element + "]");
        element.operation();
    }

    @Override
    public void visit(Element2 element) {
        System.out.println("VisitorB.visit() called with: element = [" + element + "]");
        element.operation();
    }
}
