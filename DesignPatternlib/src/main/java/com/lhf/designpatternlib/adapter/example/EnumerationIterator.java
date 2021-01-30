package com.lhf.designpatternlib.adapter.example;

import java.util.Enumeration;
import java.util.Iterator;

/**
 * Created by Joshua on 2021/1/30 16:40
 */
public class EnumerationIterator<E> implements Iterator<E> {

    Enumeration<E> enumeration;

    public EnumerationIterator(Enumeration<E> enumeration) {
        this.enumeration = enumeration;
    }

    @Override
    public boolean hasNext() {
        return enumeration.hasMoreElements();
    }

    @Override
    public E next() {
        return enumeration.nextElement();
    }
}
