package com.lhf.designpatternlib.bridge;

/**
 * Created by Joshua on 2020/4/4.
 */
public class Wallet extends Bag {
    @Override
    public void show() {
        System.out.println("Wallet.show: this is a " + color.getColor() + " Wallet");
    }
}
