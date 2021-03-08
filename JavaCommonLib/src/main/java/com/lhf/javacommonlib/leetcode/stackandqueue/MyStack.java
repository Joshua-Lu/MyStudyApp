package com.lhf.javacommonlib.leetcode.stackandqueue;

import org.junit.Test;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Created by Joshua on 2021/3/8 23:34
 */
public class MyStack<E> implements IStack<E> {

    private Object[] data;
    int count;

    public MyStack() {
        data = new Object[10];
        count = 0;
    }

    @Override
    public E push(E e) {
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
    public E pop() {
        Object item = data[--count];
        data[count] = null;
        return ((E) item);
    }

    @Override
    public E peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return (E) data[count - 1];
    }

    @Override
    public boolean empty() {
        return count == 0;
    }

    @Override
    public int search(Object o) {
        if (o == null) {
            for (int i = count - 1; i >= 0; i--) {
                if (data[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = count - 1; i >= 0; i--) {
                if (o.equals(data[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "MyStack{" +
                "data=" + Arrays.toString(data) +
                ", count=" + count +
                '}';
    }

    @Test
    public void test() {
        MyStack<Integer> stack = new MyStack<>();
        stack.push(1);
        System.out.println("MyStack.test: stack = [" + stack + "]");
    }
}
