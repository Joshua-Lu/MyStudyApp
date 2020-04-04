package com.lhf.designpatternlib.flyweight;

import java.util.HashMap;

/**
 * Created by Joshua on 2020/4/4.
 */
public class FlyweightFactory {
    private HashMap<String, IFlyweight> flyweights = new HashMap<>();

    public IFlyweight getFlyweight(String key) {
        IFlyweight flyweight = (IFlyweight) flyweights.get(key);
        if (flyweight != null) {
            System.out.println("flyweight " + key + " has existed!");
        } else {
            flyweight = new ConcreteFlyweight(key);
            flyweights.put(key, flyweight);
        }
        return flyweight;
    }
}
