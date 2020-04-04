package com.lhf.designpatternlib.composite;

/**
 * Created by Joshua on 2020/4/4 20:56.
 */
public class Leaf implements IComposite {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("Leaf.operation: name = [" + name + "]");
    }
}
