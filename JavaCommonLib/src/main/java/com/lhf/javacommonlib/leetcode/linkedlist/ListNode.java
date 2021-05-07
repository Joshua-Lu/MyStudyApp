package com.lhf.javacommonlib.leetcode.linkedlist;

import java.util.Objects;

/**
 * 单向链表Node
 * Definition for singly-linked list.
 * <p>
 * Created by Joshua on 2021/1/28.
 */
public class ListNode {
    public int val;
    public ListNode next;
    // 不为 -1 表示是有环列表的最后一个Node，
    // 并且该Node的next指向链表的第 cyclePosition 个Node
    int cyclePosition = -1;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode createList(int[] nodeValues) {
        if (nodeValues == null || nodeValues.length == 0) {
            return null;
        }
        ListNode dummyRoot = new ListNode(0);
        ListNode prev = dummyRoot;
        for (int item : nodeValues) {
            prev.next = new ListNode(item);
            prev = prev.next;
        }
        return dummyRoot.next;
    }

    public static ListNode createListWithCycle(int[] nodeValues, int cyclePosition) {
        if (nodeValues == null || nodeValues.length == 0) {
            return null;
        }
        ListNode dummyRoot = new ListNode(0);
        ListNode prev = dummyRoot;
        ListNode cycleStartNode = null;
        for (int i = 0; i < nodeValues.length; i++) {
            ListNode newNode = new ListNode(nodeValues[i]);
            prev.next = newNode;
            prev = prev.next;
            if (i == cyclePosition) {
                cycleStartNode = newNode;
            } else if (i == nodeValues.length - 1) {
                newNode.next = cycleStartNode;
                newNode.cyclePosition = cyclePosition;
            }
        }
        return dummyRoot.next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListNode listNode = (ListNode) o;
        // 其中一个是环的最后一个节点，则不需要比next了，否则会无限循环
        if (cyclePosition != -1 || listNode.cyclePosition != -1) {
            return val == listNode.val;
        } else {
            return val == listNode.val
                    && Objects.equals(next, listNode.next);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next, cyclePosition);
    }

    @Override
    public String toString() {
        ListNode node = this;
        StringBuilder result = new StringBuilder("[");
        while (node != null && node.cyclePosition == -1) {
            result.append(node.val).append("->");
            node = node.next;
        }

        if (node != null) {// 这个是带环链表的最后一个Node
            result.append(node.val).append(", cyclePosition:").append(node.cyclePosition);
        } else {
            result.delete(result.length() - 2, result.length());
        }
        result.append("]");
        return result.toString();
    }
}
