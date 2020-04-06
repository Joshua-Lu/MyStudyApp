package com.lhf.designpatternlib.iterator;

/**
 * Created by Joshua on 2020/4/6 21:55.
 */
public interface IIterator {
    Object first();

    Object next();

    boolean hasNext();
}
