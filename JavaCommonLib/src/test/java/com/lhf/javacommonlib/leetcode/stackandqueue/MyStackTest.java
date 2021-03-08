package com.lhf.javacommonlib.leetcode.stackandqueue;

import org.junit.Test;

/**
 * Created by Joshua on 2021/3/9 0:04
 */
public class MyStackTest {

    MyStack<Integer> stack = new MyStack<>();

    @Test
    public void push() {
        for (int i = 0; i < 12; i++) {
            stack.push(i);
        }
        System.out.println("MyStackTest.push: stack = [" + stack + "]");
    }

    @Test
    public void pop() {
        push();
        Integer pop = stack.pop();
        System.out.println("MyStackTest.pop: pop = [" + pop + "]");
        System.out.println("MyStackTest.pop: stack = [" + stack + "]");
    }

    @Test
    public void peek() {
        push();
        Integer peek = stack.peek();
        System.out.println("MyStackTest.peek: peek = [" + peek + "]");
        System.out.println("MyStackTest.peek: stack = [" + stack + "]");
    }

    @Test
    public void empty() {
        System.out.println("MyStackTest.empty: stack = [" + stack + "]");
        System.out.println("MyStackTest.empty: stack.empty() = [" + stack.empty() + "]");
        push();
        System.out.println("MyStackTest.empty: stack = [" + stack + "]");
        System.out.println("MyStackTest.empty: stack.empty() = [" + stack.empty() + "]");
    }

    @Test
    public void search() {
        push();
        System.out.println("MyStackTest.search: stack = [" + stack + "]");
        int search = stack.search(5);
        System.out.println("MyStackTest.search: search = [" + search + "]");
    }
}