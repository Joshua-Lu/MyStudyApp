package com.lhf.javacommonlib.leetcode.linkedlist;

import java.util.Objects;

/**
 * Definition for singly-linked list.
 * <p>
 * Created by Joshua on 2021/1/28.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    ListNode(int[] nodeValues) {
        if (nodeValues == null || nodeValues.length == 0) {
            return;
        }
        ListNode dummyRoot = new ListNode(0);
        ListNode ptr = dummyRoot;
        for (int item : nodeValues) {
            ptr.next = new ListNode(item);
            ptr = ptr.next;
        }
        val = dummyRoot.next.val;
        next = dummyRoot.next.next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        return val == listNode.val &&
                Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    @Override
    public String toString() {
        ListNode node = this;
        StringBuilder result = new StringBuilder();
        while (node != null) {
            result.append(node.val).append("->");
            node = node.next;
        }
        return "[" + result.substring(0, result.length() - 2) + "]";
    }
}
