package com.lhf.javacommonlib.leetcode.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/1/28.
 */
public class MyDoubleLinkedListTest {

    @Test
    public void get() {
        MyDoubleLinkedList linkedList = new MyDoubleLinkedList(new int[]{0, 1, 2, 3});
        int result = linkedList.get(2);
        Assert.assertEquals(2, result);
        System.out.println("MyDoubleLinkedListTest.get: result = [" + result + "]");
    }

    @Test
    public void addAtHead() {
    }

    @Test
    public void addAtTail() {
        MyDoubleLinkedList linkedList = new MyDoubleLinkedList();
        linkedList.addAtTail(1);
        System.out.println(linkedList);
    }

    @Test
    public void addAtIndex() {
        MyDoubleLinkedList linkedList = new MyDoubleLinkedList(new int[]{0, 1, 2, 3});
        System.out.println(linkedList);
        linkedList.addAtIndex(3, 5);
        System.out.println(linkedList);
    }

    @Test
    public void deleteAtIndex() {
        MyDoubleLinkedList linkedList = new MyDoubleLinkedList(new int[]{0, 1, 2, 3, 4});
        System.out.println(linkedList);
        linkedList.deleteAtIndex(3);
        System.out.println(linkedList);
    }

    @Test
    public void test() {
        MyDoubleLinkedList linkedList = new MyDoubleLinkedList();
        linkedList.addAtHead(1);
        System.out.println(linkedList);
        linkedList.addAtTail(3);
        System.out.println(linkedList);
        linkedList.addAtIndex(1, 2);   //链表变为1-> 2-> 3
        System.out.println(linkedList);
        int result = linkedList.get(1);//返回2
        Assert.assertEquals(2, result);
        System.out.println(linkedList);
        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        System.out.println(linkedList);
        result = linkedList.get(1);            //返回3
        Assert.assertEquals(3, result);
        System.out.println(linkedList);
    }
}