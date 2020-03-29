package com.lhf.designpatternlib.builder;

/**
 * 实现 AbstractBuilder 接口，完成复杂产品的各个部件的具体创建方法。
 * Created by Joshua on 2020/3/29 15:22.
 */
public class Builder1 extends AbstractBuilder {
    @Override
    public AbstractBuilder buildPartA() {
        product.setPartA("A1");
        return this;
    }

    @Override
    public AbstractBuilder buildPartB() {
        product.setPartB("B1");
        return this;
    }

    @Override
    public AbstractBuilder buildPartC() {
        product.setPartC("C1");
        return this;
    }
}
