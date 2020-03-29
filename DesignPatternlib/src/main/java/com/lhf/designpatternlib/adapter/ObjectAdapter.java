package com.lhf.designpatternlib.adapter;

/**
 * Created by Joshua on 2020/3/29 21:32.
 */
public class ObjectAdapter implements Target {
    private Adaptee adaptee;

    public ObjectAdapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }


    @Override
    public void request() {
        System.out.println("ObjectAdapter.request() called");
        if (adaptee != null) {
            adaptee.specificRequest();
        }
    }
}
