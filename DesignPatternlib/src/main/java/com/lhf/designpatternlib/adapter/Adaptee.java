package com.lhf.designpatternlib.adapter;

/**
 * 它是被访问和适配的现存组件库中的组件接口。
 * Created by Joshua on 2020/3/29 21:27.
 */
public class Adaptee {
    public void specificRequest() {
        System.out.println("Adaptee.specificRequest() called");
    }
}
