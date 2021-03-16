package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by Joshua on 2021/3/16.
 */
public class MinDepth {
    /*
    https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
    111. 二叉树的最小深度
    给定一个二叉树，找出其最小深度。

    最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

    说明：叶子节点是指没有子节点的节点。

    示例 1：
        输入：root = [3,9,20,null,null,15,7]
        输出：2
    示例 2：
        输入：root = [2,null,3,null,4,null,5,null,6]
        输出：5


    提示：
    树中节点数的范围在 [0, 105] 内
    -1000 <= Node.val <= 1000*/

    @Test
    public void test() {
        TreeNode root;
        int expected;
        int result;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        expected = 2;
        result = minDepth(root);
        Assert.assertEquals(expected, result);

        // 示例 2
        root = TreeNode.createTree(new Integer[]{2, null, 3, null, 4, null, 5, null, 6});
        expected = 5;
        result = minDepth(root);
        Assert.assertEquals(expected, result);

    }

    public int minDepth(TreeNode root) {
//        return minDepth1(root);
        return minDepth2(root);
    }

    /**
     * 递归法，相比求MaxDepth要复杂点
     * 因为最小深度是从根节点到最近**叶子节点**的最短路径上的节点数量
     */
    public int minDepth1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = minDepth1(root.left);
        int rightDepth = minDepth1(root.right);
        if (root.left == null) {
            return rightDepth + 1;
        }
        if (root.right == null) {
            return leftDepth + 1;
        }
        // 左右结点都不为null
        return Math.min(leftDepth, rightDepth) + 1;
    }

    /**
     * 迭代法，层序遍历
     * 比递归法要快，从上往下，找到最近的叶子结点就可以返回depth了，不需要遍历所有
     */
    public int minDepth2(TreeNode root) {
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
                if (poll.left == null && poll.right == null) {
                    // 是叶子结点，直接返回depth，因为从上往下遍历，所以该值就是最小值
                    return depth;
                }
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
