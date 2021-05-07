package com.lhf.javacommonlib.interview;

import com.lhf.javacommonlib.leetcode.linkedlist.ListNode;

/**
 * 删除倒数第n个节点
 *
 * @author Joshua
 * @date 2021/4/29 20:43
 */
public class DeleteNode {

    public static void main(String[] args) {
        ListNode head = ListNode.createList(new int[]{1, 2, 3, 4, 5});
        int n = 2;
        ListNode result = deleteNode(head, 2);
        System.out.println("DeleteNode.main: result = [" + result + "]");
    }

    private static ListNode deleteNode(ListNode head, int n) {
        ListNode dummyNode = new ListNode(0);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        ListNode cur = head;
        for (int i = 0; i < n - 1; i++) {
            cur = cur.next;
        }
        while (cur.next != null) {
            cur = cur.next;
            pre = pre.next;
        }
        pre.next = pre.next.next;
        return dummyNode.next;
    }


}
