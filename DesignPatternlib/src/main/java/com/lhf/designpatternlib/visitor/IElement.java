package com.lhf.designpatternlib.visitor;

/**
 * Created by Joshua on 2020/4/6 22:46.
 */
public interface IElement {
    void accept(IVisitor visitor);
}
