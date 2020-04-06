package com.lhf.designpatternlib.iterator;

/**
 * Created by Joshua on 2020/4/6 21:54.
 */
public interface IAggregate {
    void add(Object obj);

    void remove(Object obj);

    IIterator getIterator();
}
