package com.lhf.javacommonlib.leetcode.stackandqueue;

import java.util.Stack;

/**
 * 使用栈，实现队列
 * Created by Joshua on 2021/3/9 0:29
 */
public class MyQueue1<E> implements IQueue<E> {

    Stack<E> inStack;
    Stack<E> outStack;

    public MyQueue1() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    @Override
    public E offer(E e) {
        inStack.push(e);
        return e;
    }

    @Override
    public E poll() {
        E pollItem = peek();
        outStack.pop();
        return pollItem;
    }

    @Override
    public E peek() {
        if (outStack.empty()) {// outStack为空时，才需要将inStack里的移到outStack
            while (!inStack.empty()) {
                outStack.push(inStack.pop());
            }
        }
        return outStack.peek();
    }

    @Override
    public boolean empty() {
        return inStack.empty() && outStack.empty();
    }

    @Override
    public String toString() {
        return "MyQueue1{" +
                "inStack=" + inStack +
                ", outStack=" + outStack +
                '}';
    }
}
