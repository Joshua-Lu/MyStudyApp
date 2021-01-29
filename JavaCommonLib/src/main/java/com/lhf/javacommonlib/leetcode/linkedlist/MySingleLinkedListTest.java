package com.lhf.javacommonlib.leetcode.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/1/28.
 */
public class MySingleLinkedListTest {

    @Test
    public void get() {
        MySingleLinkedList linkedList = new MySingleLinkedList(new int[]{0, 1, 2, 3});
        int result = linkedList.get(2);
        System.out.println("MySingleLinkedListTest.get: result = [" + result + "]");
    }

    @Test
    public void addAtHead() {
    }

    @Test
    public void addAtTail() {
        MySingleLinkedList linkedList = new MySingleLinkedList();
        linkedList.addAtTail(1);
        System.out.println(linkedList);
    }

    @Test
    public void addAtIndex() {
    }

    @Test
    public void deleteAtIndex() {
        MySingleLinkedList linkedList = new MySingleLinkedList();
        linkedList.addAtHead(2);
        System.out.println(linkedList);
        linkedList.deleteAtIndex(1);
        linkedList.addAtHead(2);
        linkedList.addAtHead(7);
        linkedList.addAtHead(3);
        linkedList.addAtHead(2);
        linkedList.addAtHead(5);
        linkedList.addAtTail(5);
        linkedList.get(5);
        linkedList.deleteAtIndex(6);
        System.out.println(linkedList);
        linkedList.deleteAtIndex(4);
    }

    @Test
    public void test() {
        MySingleLinkedList linkedList = new MySingleLinkedList();
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