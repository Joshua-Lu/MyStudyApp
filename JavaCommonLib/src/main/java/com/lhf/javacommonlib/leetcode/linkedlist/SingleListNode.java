package com.lhf.javacommonlib.leetcode.linkedlist;

import java.util.Objects;

/**
 * 单向链表Node
 * Definition for singly-linked list.
 * <p>
 * Created by Joshua on 2021/1/28.
 */
public class SingleListNode {
    int val;
    SingleListNode next;

    SingleListNode() {
    }

    SingleListNode(int val) {
        this.val = val;
    }

    SingleListNode(int val, SingleListNode next) {
        this.val = val;
        this.next = next;
    }

    SingleListNode(int[] nodeValues) {
        if (nodeValues == null || nodeValues.length == 0) {
            return;
        }
        SingleListNode dummyRoot = new SingleListNode(0);
        SingleListNode prev = dummyRoot;
        for (int item : nodeValues) {
            prev.next = new SingleListNode(item);
            prev = prev.next;
        }
        val = dummyRoot.next.val;
        next = dummyRoot.next.next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SingleListNode listNode = (SingleListNode) o;
        return val == listNode.val &&
                Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    @Override
    public String toString() {
        SingleListNode node = this;
        StringBuilder result = new StringBuilder();
        while (node != null) {
            result.append(node.val).append("->");
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
}
