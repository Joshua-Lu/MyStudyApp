package com.lhf.designpatternlib.composite;

import java.util.ArrayList;

/**
 * Created by Joshua on 2020/4/4 20:57.
 */
public class Root implements IComposite {
    private ArrayList<IComposite> children = new ArrayList<IComposite>();

    public void add(IComposite c) {
        children.add(c);
    }

    public void remove(IComposite c) {
        children.remove(c);
    }

    public IComposite getChild(int i) {
        return children.get(i);
    }

    @Override
    public void operation() {
        System.out.println("Root.operation() called");
        for (IComposite c : children) {
            c.operation();
        }
    }
}
