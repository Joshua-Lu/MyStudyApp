package com.lhf.javacommonlib.leetcode.stackandqueue;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 解决滑动窗口中最大值问题用到的单调递减队列
 * <p>
 * 设计单调队列的时候，poll，和offer操作要保持如下规则：
 * <p>
 * poll(value)：如果窗口移除的元素value等于单调队列的出口元素，那么队列弹出元素，否则不用任何操作
 * offer(value)：如果push的元素value大于入口元素的数值，那么就将队列入口的元素弹出，直到offer元素的数值小于**等于**队列入口元素的数值为止
 * <p>
 * 保持如上规则，每次窗口移动的时候，只要问que.peek()就可以返回当前窗口的最大值。
 * <p>
 * Created by Joshua on 2021/3/11.
 */
public class MyDescQueue {
    // 使用Deque来实现
    Deque<Integer> deque = new ArrayDeque<>();

    public void poll(int value) {
        System.out.println("MyDescQueue.poll() called with: value = [" + value + "]");
        if (!deque.isEmpty() && deque.peek() == value) {
            deque.poll();
        }
        System.out.println("MyDescQueue.poll: deque = [" + deque + "]");
    }

    public void offer(int value) {
        System.out.println("MyDescQueue.offer() called with: value = [" + value + "]");
        while (!deque.isEmpty() && value > deque.peekLast()) {
            deque.pollLast();
        }
        deque.offer(value);
        System.out.println("MyDescQueue.offer: deque = [" + deque + "]");
    }

    public int peek() {
        return deque.peek();
    }

    @Override
    public String toString() {
        return "MyDescQueue{" +
                "deque=" + deque +
                '}';
    }
}
