package com.lhf.javacommonlib.leetcode.stackandqueue;

import org.junit.Test;

import java.util.EmptyStackException;
import java.util.LinkedList;

/**
 * 使用队列，实现栈
 * Created by Joshua on 2021/3/8 23:34
 */
public class MyStack1<E> implements IStack<E> {

    // LinkedList也是一种队列
    private LinkedList<E> queue1;// 用来存储数据
    private LinkedList<E> queue2;// 辅助队列
    int count;

    public MyStack1() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
        count = 0;
    }

    @Override
    public E push(E e) {
        if (queue1.isEmpty()) {
            queue1.offer(e);
        } else {
            queue2.offer(e);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            LinkedList<E> tmp = queue1;
            queue1 = queue2;
            queue2 = tmp;
        }
        count++;
        return e;
    }

    @Override
    public E pop() {
        E item = peek();
        queue1.poll();
        count--;
        return item;
    }

    @Override
    public E peek() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return queue1.peek();
    }

    @Override
    public boolean empty() {
        return queue1.isEmpty();
    }

    @Override
    public int search(Object o) {
        if (empty()) {
            throw new EmptyStackException();
        }
        int index = count;
        if (o == null) {
            while (!queue1.isEmpty()) {
                E poll = queue1.poll();
                index--;
                queue2.offer(poll);
                if (poll == null) {
                    while (!queue1.isEmpty()) {
                        queue2.offer(queue1.poll());
                    }
                    LinkedList<E> tmp = queue1;
                    queue1 = queue2;
                    queue2 = tmp;
                    return index;
                }
            }
        } else {
            while (!queue1.isEmpty()) {
                E poll = queue1.poll();
                index--;
                queue2.offer(poll);
                if (o.equals(poll)) {
                    while (!queue1.isEmpty()) {
                        queue2.offer(queue1.poll());
                    }
                    LinkedList<E> tmp = queue1;
                    queue1 = queue2;
                    queue2 = tmp;
                    return index;
                }
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        return "MyStack1{" +
                "queue1=" + queue1 +
                ", queue2=" + queue2 +
                '}';
    }

    @Test
    public void test() {
        MyStack1<Integer> stack = new MyStack1<>();
        stack.push(1);
        System.out.println("MyStack.test: stack = [" + stack + "]");
    }
}
