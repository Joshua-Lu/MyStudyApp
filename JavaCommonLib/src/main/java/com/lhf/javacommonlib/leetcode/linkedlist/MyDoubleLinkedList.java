package com.lhf.javacommonlib.leetcode.linkedlist;

/**
 * 单向链表
 * <p>
 * Created by Joshua on 2021/1/28.
 */
public class MyDoubleLinkedList {
    /*
    https://leetcode-cn.com/problems/design-linked-list/
    707. 设计链表
    设计链表的实现。您可以选择使用单链表或双链表。单链表中的节点应该具有两个属性：val 和 next。
    val 是当前节点的值，next 是指向下一个节点的指针/引用。
    如果要使用双向链表，则还需要一个属性 prev 以指示链表中的上一个节点。
    假设链表中的所有节点都是 0-index 的。

    在链表类中实现这些功能：
        get(index)：获取链表中第 index 个节点的值。如果索引无效，则返回-1。
        addAtHead(val)：在链表的第一个元素之前添加一个值为 val 的节点。插入后，新节点将成为链表的第一个节点。
        addAtTail(val)：将值为 val 的节点追加到链表的最后一个元素。
        addAtIndex(index,val)：在链表中的第 index 个节点之前添加值为 val  的节点。如果 index 等于链表的长度，则该节点将附加到链表的末尾。如果 index 大于链表长度，则不会插入节点。如果index小于0，则在头部插入节点。
        deleteAtIndex(index)：如果索引 index 有效，则删除链表中的第 index 个节点。


    示例：
        MyDoubleLinkedList linkedList = new MyDoubleLinkedList();
        linkedList.addAtHead(1);
        linkedList.addAtTail(3);
        linkedList.addAtIndex(1,2);   //链表变为1-> 2-> 3
        linkedList.get(1);            //返回2
        linkedList.deleteAtIndex(1);  //现在链表是1-> 3
        linkedList.get(1);            //返回3


    提示：
        所有val值都在 [1, 1000] 之内。
        操作次数将在  [1, 1000] 之内。
        请不要使用内置的 LinkedList 库。
    */

    // 虚拟头结点
    private DoubleListNode dummyHead;
    // 虚拟尾结点
    private DoubleListNode dummyTail;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public MyDoubleLinkedList() {
        dummyHead = new DoubleListNode();
        dummyTail = new DoubleListNode();
        dummyHead.next = dummyTail;
        dummyTail.prev = dummyHead;
        size = 0;
    }

    public MyDoubleLinkedList(int[] nodeValues) {
        this();
        for (int nodeValue : nodeValues) {
            addAtTail(nodeValue);
        }
    }

    public int size() {
        return size;
    }

    private boolean isBeforeMid(int index) {
        return index < size / 2;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        System.out.println("MyDoubleLinkedList.get() called with: index = [" + index + "]");
        if (index < 0 || index >= size) {
            System.err.println("MyDoubleLinkedList.deleteAtIndex: size = [" + size + "]");
            return -1;
        }
        DoubleListNode current = null;
        if (isBeforeMid(index)) {
            current = dummyHead.next;
            while (index > 0) {
                current = current.next;
                index--;
            }
        } else {
            current = dummyTail.prev;
            while (index < size - 1) {
                current = current.prev;
                index++;
            }
        }
        return current.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        System.out.println("MyDoubleLinkedList.addAtHead() called with: val = [" + val + "]");
        DoubleListNode newNode = new DoubleListNode(dummyHead, val, dummyHead.next);
        dummyHead.next.prev = newNode;
        dummyHead.next = newNode;
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        System.out.println("MyDoubleLinkedList.addAtTail() called with: val = [" + val + "]");
        DoubleListNode newNode = new DoubleListNode(dummyTail.prev, val, dummyTail);
        dummyTail.prev.next = newNode;
        dummyTail.prev = newNode;
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        System.out.println("MyDoubleLinkedList.addAtIndex() called with: index = [" + index + "], val = [" + val + "]");
        if (index < 0 || index > size) {
            System.err.println("MyDoubleLinkedList.deleteAtIndex: size = [" + size + "]");
            return;
        }
        if (isBeforeMid(index)) {
            DoubleListNode prev = dummyHead;
            while (index > 0) {
                prev = prev.next;
                index--;
            }
            // 循环结束，此时 prev 是要插入位置的前一个结点
            DoubleListNode newNode = new DoubleListNode(prev, val, prev.next);
            prev.next.prev = newNode;
            prev.next = newNode;
        } else {
            DoubleListNode next = dummyTail;
            while (index < size) {
                next = next.prev;
                index++;
            }
            // 循环结束，此时 next 是要插入位置的后一个结点
            DoubleListNode newNode = new DoubleListNode(next.prev, val, next);
            next.prev.next = newNode;
            next.prev = newNode;
        }
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        System.out.println("MyDoubleLinkedList.deleteAtIndex() called with: index = [" + index + "]");
        if (index < 0 || index >= size) {
            System.err.println("MyDoubleLinkedList.deleteAtIndex: size = [" + size + "]");
            return;
        }
        if (isBeforeMid(index)) {
            DoubleListNode prev = dummyHead;
            while (index > 0) {
                prev = prev.next;
                index--;
            }
            // 循环结束，此时 prev 是要删除位置的前一个结点
            DoubleListNode toDelete = prev.next;
            toDelete.next.prev = prev;
            prev.next = toDelete.next;
            // 将要删除的结点的prev和next变量置空，释放该结点内存
            toDelete.prev = null;
            toDelete.next = null;
        } else {
            DoubleListNode next = dummyTail;
            while (index < size - 1) {
                next = next.prev;
                index++;
            }
            // 循环结束，此时 next 是要删除位置的后一个结点
            DoubleListNode toDelete = next.prev;
            toDelete.prev.next = next;
            next.prev = toDelete.prev;
            // 将要删除的结点的prev和next变量置空，释放该结点内存
            toDelete.prev = null;
            toDelete.next = null;
        }
        size--;
    }

    @Override
    public String toString() {
        return "MyDoubleLinkedList:" + dummyHead.next;
    }
}
