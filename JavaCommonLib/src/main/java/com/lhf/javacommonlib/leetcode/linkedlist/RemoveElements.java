package com.lhf.javacommonlib.leetcode.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/1/28.
 */
public class RemoveElements {
    /*
    https://leetcode-cn.com/problems/remove-linked-list-elements/
    203. 移除链表元素
    删除链表中等于给定值 val 的所有节点。

    示例:
        输入: 1->2->6->3->4->5->6, val = 6
        输出: 1->2->3->4->5
    */

    @Test
    public void test() {
        SingleListNode head;
        int val;
        SingleListNode expected;
        SingleListNode result;

        // 示例 1
        head = new SingleListNode(new int[]{1, 2, 6, 3, 4, 5, 6});
        val = 6;
        expected = new SingleListNode(new int[]{1, 2, 3, 4, 5});
        result = removeElements(head, val);
        Assert.assertEquals(expected, result);

    }

    /**
     * 思路：加一个虚拟头结点，这样删除头结点和删除其他结点的操作一致
     * <p>
     * 时间：O(n)
     * 空间：O(1)
     */
    public SingleListNode removeElements(SingleListNode head, int val) {
        System.out.println("RemoveElements.removeElements() called with: head = [" + head + "], val = [" + val + "]");
        // 加一个虚拟头结点，这样删除头结点和删除其他结点的操作一致
        SingleListNode dummyRoot = new SingleListNode(0, head);
        SingleListNode pre = dummyRoot;
        while (pre.next != null) {
            if (pre.next.val == val) {
                pre.next = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        System.out.println("RemoveElements.removeElements() returned: " + dummyRoot.next);
        return dummyRoot.next;
    }
}
