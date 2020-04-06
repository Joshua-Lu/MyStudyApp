package com.lhf.designpatternlib.iterator;

import java.util.List;

/**
 * 迭代器模式
 * 提供一个对象来顺序访问聚合对象中的一系列数据，而不暴露聚合对象的内部表示。
 * Created by Joshua on 2020/4/6 21:57.
 */
public class Iterator implements IIterator {
    private List<Object> list = null;
    private int index = -1;

    public Iterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public Object first() {
        index = 0;
        Object obj = list.get(index);
        System.out.println("Iterator.first: obj = [" + obj.toString() + "]");
        return obj;
    }

    @Override
    public Object next() {
        Object obj = null;
        if (this.hasNext()) {
            obj = list.get(++index);
        }
        System.out.println("Iterator.next: obj = [" + (obj == null ? obj : obj.toString()) + "]");
        return obj;
    }

    @Override
    public boolean hasNext() {
        if (index < list.size() - 1) {
            return true;
        } else {
            return false;
        }
    }
}
