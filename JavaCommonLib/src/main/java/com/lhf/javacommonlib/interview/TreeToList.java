package com.lhf.javacommonlib.interview;

import com.lhf.javacommonlib.leetcode.tree.TreeNode;

import org.junit.Test;

/**
 * 二叉搜索树转成双向链表，并且不能新建节点，要直接修改指针
 * 注：快手面试题
 *
 * @author Joshua
 * @date 2021/5/13 21:20
 */
public class TreeToList {
    @Test
    public void test() {
        TreeNode root = TreeNode.createTree(new Integer[]{4, 2, 6, 1, 3, 5, 7});
        // 要返回头节点，isLeft传false，返回尾节点，isLeft传true
        TreeNode head = treeToList(root, false);

        TreeNode tail = null;
        System.out.println("====================left to right=====================");
        while (head != null) {
            System.out.print(head.val + " ");
            tail = head;
            head = head.right;
        }
        System.out.println("");
        System.out.println("====================right to left=====================");
        while (tail != null) {
            System.out.print(tail.val + " ");
            tail = tail.left;
        }
    }

    private TreeNode treeToList(TreeNode root, boolean isLeft) {
        if (root == null) {
            return null;
        }
        TreeNode left = treeToList(root.left, true);
        TreeNode right = treeToList(root.right, false);
        if (left != null) {
            root.left = left;
            left.right = root;
        }
        if (right != null) {
            root.right = right;
            right.left = root;
        }
        if (isLeft) {
            // 左子树返回尾节点
            TreeNode tail = root;
            while (tail.right != null) {
                tail = tail.right;
            }
            return tail;
        } else {
            // 有子树返回头节点
            TreeNode head = root;
            while (head.left != null) {
                head = head.left;
            }
            return head;
        }
    }
}
