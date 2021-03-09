package com.lhf.javacommonlib.leetcode.stackandqueue;

import java.util.Arrays;

/**
 * Created by Joshua on 2021/3/9 0:29
 */
public class MyQueue<E> implements IQueue<E> {

    Object[] data;
    int count;

    public MyQueue() {
        data = new Object[10];
        count = 0;
    }

    @Override
    public E offer(E e) {
        ensureCapacity(count + 1);
        data[count++] = e;
        return e;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > data.length) {
            grow(minCapacity);
        }
    }

    private void grow(int minCapacity) {
        int newLength = minCapacity + (minCapacity >> 1);
        data = Arrays.copyOf(data, newLength);
    }

    @Override
    public E poll() {
        E pollItem = (E) data[0];
        data[0] = null;
        count--;
        System.arraycopy(data, 1, data, 0, data.length - 1);
        return pollItem;
    }

    @Override
    public E peek() {
        return (E) data[0];
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "data=" + Arrays.toString(data) +
                ", count=" + count +
                '}';
    }
}
