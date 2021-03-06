package com.lhf.javacommonlib.leetcode.stackandqueue;

import org.junit.Test;

/**
 * Created by Joshua on 2021/3/9 0:48
 */
public class MyQueueTest {

//    IQueue<Integer> queue = new MyQueue<>();
IQueue<Integer> queue = new MyQueue1<>();

    @Test
    public void offer() {
        for (int i = 0; i < 13; i++) {
            queue.offer(i);
        }
        System.out.println("MyQueueTest.offer: queue = [" + queue + "]");
    }

    @Test
    public void poll() {
        offer();
        System.out.println("MyQueueTest.poll: queue = [" + queue + "]");
        Integer poll = queue.poll();
        System.out.println("MyQueueTest.poll: poll = [" + poll + "]");
        System.out.println("MyQueueTest.poll: queue = [" + queue + "]");
        poll = queue.poll();
        System.out.println("MyQueueTest.poll: poll = [" + poll + "]");
        System.out.println("MyQueueTest.poll: queue = [" + queue + "]");
    }

    @Test
    public void peek() {
        offer();
        System.out.println("MyQueueTest.peek: queue = [" + queue + "]");
        Integer peek = queue.peek();
        System.out.println("MyQueueTest.peek: peek = [" + peek + "]");
        System.out.println("MyQueueTest.peek: queue = [" + queue + "]");
    }

    @Test
    public void test() {
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        queue.offer(4);
        queue.poll();
        queue.offer(5);
        System.out.println("MyQueueTest.test: queue = [" + queue + "]");
        Integer poll = queue.poll();
        System.out.println("MyQueueTest.test: poll = [" + poll + "]");
        queue.poll();
        queue.poll();
        queue.poll();
    }
}