package com.lhf.designpatternlib.prototype;

/**
 * Created by Joshua on 2020/4/2 23:32.
 */
public class Prototype implements Cloneable {
    public Prototype() {
        System.out.println("Prototype.Prototype() called");
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        System.out.println("Prototype.clone() called");
        return (Prototype) super.clone();
    }
}
