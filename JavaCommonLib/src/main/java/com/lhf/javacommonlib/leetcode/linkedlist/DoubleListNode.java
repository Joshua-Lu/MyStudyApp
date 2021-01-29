package com.lhf.javacommonlib.leetcode.linkedlist;

import java.util.Objects;

/**
 * 双向链表
 * Definition for double linked list.
 * <p>
 * Created by Joshua on 2021/1/28.
 */
public class DoubleListNode {
    int val;
    DoubleListNode next;
    DoubleListNode prev;

    DoubleListNode() {
    }

    DoubleListNode(int val) {
        this.val = val;
    }

    DoubleListNode(DoubleListNode prev, int val, DoubleListNode next) {
        this.val = val;
        this.next = next;
        this.prev = prev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoubleListNode listNode = (DoubleListNode) o;
        return val == listNode.val &&
                Objects.equals(next, listNode.next) &&
                Objects.equals(prev, listNode.prev);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next, prev);
    }

    @Override
    public String toString() {
        DoubleListNode node = this;
        StringBuilder result = new StringBuilder();
        while (node != null && node.next != null) {
            result.append(node.val).append("->");
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
}
