package com.lhf.javacommonlib.leetcode.stackandqueue;

import org.junit.Test;

/**
 * Created by Joshua on 2021/3/11.
 */
public class MyDescQueueTest {

    MyDescQueue queue = new MyDescQueue();

    @Test
    public void poll() {
        offer();
        queue.poll(1);
        queue.poll(2);
        queue.poll(3);
    }

    @Test
    public void offer() {
        queue.offer(7);
        queue.offer(2);
        queue.offer(4);

    }

    @Test
    public void peek() {
        offer();
        int peek = queue.peek();
        System.out.println("MyDescQueueTest.peek: peek = [" + peek + "]");
    }
}