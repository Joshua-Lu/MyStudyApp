package com.lhf.designpatternlib.bridge;

/**
 * Created by Joshua on 2020/4/4.
 */
public class HandBag extends Bag {
    @Override
    public void show() {
        System.out.println("HandBag.show: this is a " + color.getColor() + " HandBag");
    }
}
