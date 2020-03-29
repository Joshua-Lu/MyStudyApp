package com.lhf.designpatternlib.builder;

/**
 * Created by Joshua on 2020/3/29 15:22.
 */
public class Builder2 extends AbstractBuilder {
    @Override
    public AbstractBuilder buildPartA() {
        product.setPartA("A2");
        return this;
    }

    @Override
    public AbstractBuilder buildPartB() {
        product.setPartB("B2");
        return this;
    }

    @Override
    public AbstractBuilder buildPartC() {
        product.setPartC("C2");
        return this;
    }
}
