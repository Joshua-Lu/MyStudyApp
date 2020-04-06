package com.lhf.designpatternlib.template_method;

/**
 * 模板方法模式
 * 定义一个操作中的算法骨架，而将算法的一些步骤延迟到子类中，使得子类可以不改变该算法结构的情况下重定义该算法的某些特定步骤
 * Created by Joshua on 2020/4/6 16:54.
 */
public abstract class HookAbstractClass {

    public void templateMethod() {//模板方法
        System.out.println("HookAbstractClass.templateMethod() called");
        abstractMethod1();
        hookMethod1();
        if (hookMethod2()) {
            SpecificMethod();
        }
        abstractMethod2();
    }

    public void SpecificMethod() {//具体方法
        System.out.println("HookAbstractClass.SpecificMethod() called");
    }

    public void hookMethod1() {//钩子方法1
        System.out.println("HookAbstractClass.hookMethod1() called");
    }

    public boolean hookMethod2() {//钩子方法2
        System.out.println("HookAbstractClass.hookMethod2() called");
        return false;
    }

    public abstract void abstractMethod1(); //抽象方法1

    public abstract void abstractMethod2(); //抽象方法2
}
