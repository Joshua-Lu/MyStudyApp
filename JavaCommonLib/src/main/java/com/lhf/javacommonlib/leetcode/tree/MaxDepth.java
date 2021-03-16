package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Joshua on 2021/3/16.
 */
public class MaxDepth {
    /*
    https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/
    104. 二叉树的最大深度
    给定一个二叉树，找出其最大深度。

    二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

    说明: 叶子节点是指没有子节点的节点。

    示例：
    给定二叉树 [3,9,20,null,null,15,7]，

            3
           / \
          9  20
            /  \
           15   7
    返回它的最大深度 3 。*/

    @Test
    public void test() {
        TreeNode root = TreeNode.createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        int expected = 3;
        int result;
        result = maxDepth(root);
        Assert.assertEquals(expected, result);

    }

    public int maxDepth(TreeNode root) {
//        return maxDepth1(root);
        return maxDepth2(root);
    }

    /**
     * 递归法
     */
    public int maxDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth1(root.left);
        int rightDepth = maxDepth1(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 迭代法，使用层序遍历
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int depth = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }
        }
        return depth;
    }
}
