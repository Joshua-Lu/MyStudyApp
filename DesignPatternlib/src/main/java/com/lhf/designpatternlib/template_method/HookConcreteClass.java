package com.lhf.designpatternlib.template_method;

/**
 * Created by Joshua on 2020/4/6 16:56.
 */
public class HookConcreteClass extends HookAbstractClass {
    @Override
    public void abstractMethod1() {
        System.out.println("HookConcreteClass.abstractMethod1() called");
    }

    @Override
    public void abstractMethod2() {
        System.out.println("HookConcreteClass.abstractMethod2() called");
    }

    @Override
    public void hookMethod1() {
        System.out.println("HookConcreteClass.hookMethod1() called");
    }

    @Override
    public boolean hookMethod2() {
        System.out.println("HookConcreteClass.hookMethod2() called");
        return true;
    }
}
