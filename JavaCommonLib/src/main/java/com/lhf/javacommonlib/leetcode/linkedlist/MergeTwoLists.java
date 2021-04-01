package com.lhf.javacommonlib.leetcode.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author JoshuaLu
 * @date 2021/4/1 10:32
 */
public class MergeTwoLists {
    /*
    https://leetcode-cn.com/problems/merge-two-sorted-lists/
    21. 合并两个有序链表
    将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。

    示例 1：
        输入：l1 = [1,2,4], l2 = [1,3,4]
        输出：[1,1,2,3,4,4]
    示例 2：
        输入：l1 = [], l2 = []
        输出：[]
    示例 3：
        输入：l1 = [], l2 = [0]
        输出：[0]

    提示：
        两个链表的节点数目范围是 [0, 50]
        -100 <= Node.val <= 100
        l1 和 l2 均按 非递减顺序 排列*/

    @Test
    public void test() {
        ListNode l1, l2;
        ListNode exp, res;

        // 示例 1
        l1 = ListNode.createList(new int[]{1, 2, 4});
        l2 = ListNode.createList(new int[]{1, 3, 4});
        exp = ListNode.createList(new int[]{1, 1, 2, 3, 4, 4});
        res = mergeTwoLists(l1, l2);
        Assert.assertEquals(exp.toString(), res.toString());

        // 示例 1
        l1 = ListNode.createList(new int[]{});
        l2 = ListNode.createList(new int[]{});
        exp = ListNode.createList(new int[]{});
        res = mergeTwoLists(l1, l2);
        Assert.assertNull(res);

        // 示例 1
        l1 = ListNode.createList(new int[]{});
        l2 = ListNode.createList(new int[]{0});
        exp = ListNode.createList(new int[]{0});
        res = mergeTwoLists(l1, l2);
        Assert.assertEquals(exp.toString(), res.toString());

    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyRoot = new ListNode();
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode tail = dummyRoot;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        if (l1 != null) {
            tail.next = l1;
        } else if (l2 != null) {
            tail.next = l2;
        }
        return dummyRoot.next;
    }
}
