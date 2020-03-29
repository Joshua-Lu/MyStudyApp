package com.lhf.designpatternlib.builder;

/**
 * 它调用建造者对象中的部件构造与装配方法完成复杂对象的创建，在指挥者中不涉及具体产品的信息。
 * Created by Joshua on 2020/3/29 15:25.
 */
public class Director {

    private AbstractBuilder builder;

    public Director(AbstractBuilder builder) {
        this.builder = builder;
    }

    public BuilderProduct construct() {
        return builder.buildPartA()
                .buildPartB()
                .buildPartC()
                .getResult();
    }
}
