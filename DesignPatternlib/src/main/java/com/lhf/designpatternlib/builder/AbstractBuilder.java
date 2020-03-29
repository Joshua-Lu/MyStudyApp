package com.lhf.designpatternlib.builder;

/**
 * 它是一个包含创建产品各个子部件的抽象方法的接口，通常还包含一个返回复杂产品的方法 getResult()。
 * 抽象Builder，这里不能使用接口，要使用抽象类，因为需要定义一个成员变量
 * Created by Joshua on 2020/3/29 15:13.
 */
public abstract class AbstractBuilder {
    //创建产品对象
    protected BuilderProduct product = new BuilderProduct();

    public abstract AbstractBuilder buildPartA();

    public abstract AbstractBuilder buildPartB();

    public abstract AbstractBuilder buildPartC();

    //返回产品对象
    public BuilderProduct getResult() {
        return product;
    }
}
