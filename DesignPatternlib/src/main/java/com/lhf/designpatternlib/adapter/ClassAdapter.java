package com.lhf.designpatternlib.adapter;

/**
 * 它是一个转换器，通过继承或引用适配者的对象，把适配者接口转换成目标接口，让客户按目标接口的格式访问适配者。
 * Created by Joshua on 2020/3/29 21:25.
 */
public class ClassAdapter extends Adaptee implements Target {
    @Override
    public void request() {
        System.out.println("ClassAdapter.request() called");
        specificRequest();
    }
}
