package com.lhf.javacommonlib.leetcode.linkedlist;

/**
 * 单向链表
 * <p>
 * Created by Joshua on 2021/1/28.
 */
public class MySingleLinkedList {
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
        MySingleLinkedList linkedList = new MySingleLinkedList();
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
    private SingleListNode dummyRoot;
    private int size;

    /**
     * Initialize your data structure here.
     */
    public MySingleLinkedList() {
        dummyRoot = new SingleListNode();
        size = 0;
    }

    public MySingleLinkedList(int[] nodeValues) {
        this();
        dummyRoot.next = new SingleListNode(nodeValues);
    }

    public int size() {
        return size;
    }

    /**
     * Get the value of the index-th node in the linked list. If the index is invalid, return -1.
     */
    public int get(int index) {
        System.out.println("MySingleLinkedList.get() called with: index = [" + index + "]");
        if (index < 0 || index >= size) {
            System.err.println("MySingleLinkedList.deleteAtIndex: size = [" + size + "]");
            return -1;
        }
        SingleListNode current = dummyRoot.next;
        while (index > 0) {
            current = current.next;
            index--;
        }
        return current.val;
    }

    /**
     * Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list.
     */
    public void addAtHead(int val) {
        System.out.println("MySingleLinkedList.addAtHead() called with: val = [" + val + "]");
        dummyRoot.next = new SingleListNode(val, dummyRoot.next);
        size++;
    }

    /**
     * Append a node of value val to the last element of the linked list.
     */
    public void addAtTail(int val) {
        System.out.println("MySingleLinkedList.addAtTail() called with: val = [" + val + "]");
        SingleListNode current = dummyRoot;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new SingleListNode(val);
        size++;
    }

    /**
     * Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted.
     */
    public void addAtIndex(int index, int val) {
        System.out.println("MySingleLinkedList.addAtIndex() called with: index = [" + index + "], val = [" + val + "]");
        if (index < 0 || index > size) {
            System.err.println("MySingleLinkedList.deleteAtIndex: size = [" + size + "]");
            return;
        }
        SingleListNode prev = dummyRoot;
        while (index > 0) {
            prev = prev.next;
            index--;
        }
        // 循环结束，此时 prev 是要插入位置的前一个结点
        prev.next = new SingleListNode(val, prev.next);
        size++;
    }

    /**
     * Delete the index-th node in the linked list, if the index is valid.
     */
    public void deleteAtIndex(int index) {
        System.out.println("MySingleLinkedList.deleteAtIndex() called with: index = [" + index + "]");
        if (index < 0 || index >= size) {
            System.err.println("MySingleLinkedList.deleteAtIndex: size = [" + size + "]");
            return;
        }
        SingleListNode prev = dummyRoot;
        while (index > 0) {
            prev = prev.next;
            index--;
        }
        // 循环结束，此时 prev 是要删除位置的前一个结点
        SingleListNode toDelete = prev.next;
        prev.next = toDelete.next;
        toDelete.next = null;// 将要删除的结点的next变量置空，释放该结点内存
        size--;
    }

    @Override
    public String toString() {
        return "MySingleLinkedList:" + dummyRoot.next;
    }
}
