package com.lhf.designpatternlib.visitor;

/**
 * Created by Joshua on 2020/4/6 22:45.
 */
public interface IVisitor {

    void visit(Element1 element);

    void visit(Element2 element);
}
