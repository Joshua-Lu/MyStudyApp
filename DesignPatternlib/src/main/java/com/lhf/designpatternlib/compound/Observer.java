package com.lhf.designpatternlib.compound;

/**
 * Created by Joshua on 2021/2/21 1:41
 */
public class Observer implements QuackObserver {
    @Override
    public void update(Quackable observable) {
        System.out.println("Observer[" + toString() + "].update() called with: observable = [" + observable + "]");
    }
}
