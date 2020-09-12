package com.lhf.javacommonlib.common;

/**
 * Created by Joshua on 2020/9/11 21:59
 */
public class Coder extends Person {

    @Override
    public void work() throws NullPointerException {
        throw new NullPointerException();
//        System.out.println("Coder.work() called");
    }

    public void debug() {

    }
}
