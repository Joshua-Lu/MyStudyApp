package com.lhf.designpatternlib.flyweight;

/**
 * Created by Joshua on 2020/4/4.
 */
public class ConcreteFlyweight implements IFlyweight {
    private String key;

    public ConcreteFlyweight(String key) {
        System.out.println("ConcreteFlyweight.ConcreteFlyweight() called with: key = [" + key + "]");
        this.key = key;
    }

    @Override
    public void operation(UnsharedStatus status) {
        System.out.println("ConcreteFlyweight.operation: key = [" + key + "]");
        System.out.println("ConcreteFlyweight.operation() called with: status = [" + status + "]");
    }
}
