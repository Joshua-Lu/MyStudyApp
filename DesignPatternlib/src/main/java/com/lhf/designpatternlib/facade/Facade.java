package com.lhf.designpatternlib.facade;

/**
 * 外观模式
 * 结构比较简单，主要是定义了一个高层接口。
 * 它包含了对各个子系统的引用，客户端可以通过它访问各个子系统的功能。
 * Created by Joshua on 2020/4/4.
 */
public class Facade {
    private SubSystem1 subSystem1 = new SubSystem1();
    private SubSystem2 subSystem2 = new SubSystem2();

    public void method() {
        System.out.println("Facade.method() called");
        subSystem1.method1();
        subSystem2.method2();
    }
}
