package com.lhf.designpatternlib.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joshua on 2020/4/6 21:56.
 */
public class Aggregate implements IAggregate {
    private List<Object> list = new ArrayList<>();

    @Override
    public void add(Object obj) {
        list.add(obj);
    }

    @Override
    public void remove(Object obj) {
        list.remove(obj);
    }

    @Override
    public IIterator getIterator() {
        return new Iterator(list);
    }
}
