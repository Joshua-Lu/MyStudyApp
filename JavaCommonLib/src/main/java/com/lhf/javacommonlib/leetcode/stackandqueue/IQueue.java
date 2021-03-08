package com.lhf.javacommonlib.leetcode.stackandqueue;

/**
 * Created by Joshua on 2021/3/8 23:18
 */
interface IQueue<E> {
    E offer(E e);
    E poll();
    E peek();
}
