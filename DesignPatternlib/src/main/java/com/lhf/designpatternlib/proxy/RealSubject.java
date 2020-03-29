package com.lhf.designpatternlib.proxy;

/**
 * 实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
 * Created by Joshua on 2020/3/29 16:11.
 */
public class RealSubject implements ISubject {
    @Override
    public void request() {
        System.out.println("RealSubject.request() called");
    }
}
