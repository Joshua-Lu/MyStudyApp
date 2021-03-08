package com.lhf.javacommonlib.leetcode.stackandqueue;

/**
 * Created by Joshua on 2021/3/8 23:14
 */
interface IStack<E> {
    E push(E e);
    E pop();
    E peek();
    boolean empty();
    int search(Object o);
}
