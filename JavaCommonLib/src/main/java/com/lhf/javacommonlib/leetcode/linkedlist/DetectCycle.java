package com.lhf.javacommonlib.leetcode.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/2/1.
 */
public class DetectCycle {

    /*
    142. 环形链表 II
    给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
    为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。

    说明：不允许修改给定的链表。

    进阶：
    你是否可以使用 O(1) 空间解决此题？


    示例 1：
        输入：head = [3,2,0,-4], pos = 1
        输出：返回索引为 1 的链表节点
        解释：链表中有一个环，其尾部连接到第二个节点。
    示例 2：
        输入：head = [1,2], pos = 0
        输出：返回索引为 0 的链表节点
        解释：链表中有一个环，其尾部连接到第一个节点。
    示例 3：
        输入：head = [1], pos = -1
        输出：返回 null
        解释：链表中没有环。
    */

    @Test
    public void test() {
        ListNode head;
        ListNode expected;
        ListNode result;

        // 示例 1
        head = ListNode.createListWithCycle(new int[]{3, 2, 0, -4}, 1);
        expected = ListNode.createListWithCycle(new int[]{2, 0, -4}, 0);
        result = detectCycle(head);
        Assert.assertEquals(expected, result);
    }

    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {// 有环
                ListNode index1 = fast;
                ListNode index2 = head;
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }
}
