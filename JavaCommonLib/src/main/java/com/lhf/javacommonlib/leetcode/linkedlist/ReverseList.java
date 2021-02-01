package com.lhf.javacommonlib.leetcode.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/2/1.
 */
public class ReverseList {
    /*
    https://leetcode-cn.com/problems/reverse-linked-list/
    206. 反转链表
    反转一个单链表。

    示例:
        输入: 1->2->3->4->5->NULL
        输出: 5->4->3->2->1->NULL
    进阶:
        你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
    */

    @Test
    public void test() {
        ListNode head = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        ListNode expected = ListNode.createList(new int[]{5, 4, 3, 2, 1});
        ListNode result = reverseList(head);
        Assert.assertEquals(expected, result);
    }

    public ListNode reverseList(ListNode head) {
//        return reverseList1(head);
        return reverseList2(head);
    }

    /**
     * 双指针
     */
    public ListNode reverseList1(ListNode head) {
        System.out.println("ReverseList.reverseList1() called with: head = [" + head + "]");
        ListNode prev = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            temp = cur.next;// 先保存下一个节点
            cur.next = prev;// 反转
            // 更新prev、cur位置
            prev = cur;
            cur = temp;
        }
        System.out.println("ReverseList.reverseList1() returned: " + prev);
        return prev;
    }

    /**
     * 递归
     */
    public ListNode reverseList2(ListNode head) {
        System.out.println("ReverseList.reverseList1() called with: head = [" + head + "]");
        ListNode prev = null;
        ListNode cur = head;
        ListNode temp = null;
        while (cur != null) {
            temp = cur.next;// 先保存下一个节点
            cur.next = prev;// 反转
            // 更新prev、cur位置
            prev = cur;
            cur = temp;
        }
        System.out.println("ReverseList.reverseList1() returned: " + prev);
        return prev;
    }

    private ListNode reverse(ListNode prev, ListNode cur) {
        if (cur == null) {
            return prev;
        }
        ListNode temp = null;
        temp = cur.next;// 先保存下一个节点
        cur.next = prev;// 反转
        // 更新prev、cur位置
        prev = cur;
        cur = temp;
        return reverse(prev, cur);
    }
}
