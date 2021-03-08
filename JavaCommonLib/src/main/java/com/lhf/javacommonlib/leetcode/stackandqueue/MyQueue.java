package com.lhf.javacommonlib.leetcode.stackandqueue;

import java.util.Arrays;

/**
 * Created by Joshua on 2021/3/9 0:29
 */
public class MyQueue<E> implements IQueue<E> {

    Object[] data;
    int count;
    int takeIndex;
    int putIndex;

    public MyQueue() {
        data = new Object[10];
        count = 0;
        takeIndex = 0;
        putIndex = 0;
    }

    @Override
    public E offer(E e) {
        ensureCapacity(putIndex + 1);
        data[putIndex++] = e;
        count++;
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
        E pollItem = (E) data[takeIndex];
        data[takeIndex] = null;
        takeIndex++;
        count--;
        return pollItem;
    }

    @Override
    public E peek() {
        return (E) data[takeIndex];
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "data=" + Arrays.toString(data) +
                ", count=" + count +
                ", leftIndex=" + takeIndex +
                ", rightIndex=" + putIndex +
                '}';
    }
}
