package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/19.
 */
public class SumOfLeftLeaves {
    /*
    https://leetcode-cn.com/problems/sum-of-left-leaves/
    404. 左叶子之和
    计算给定二叉树的所有左叶子之和。

    示例：

             3
            / \
           9  20
             /  \
            15   7

    在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
    */

    @Test
    public void test() {
        TreeNode root = TreeNode.createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        int exp = 24;
        int res = sumOfLeftLeaves(root);
        Assert.assertEquals(exp, res);

        root = TreeNode.createTree(new Integer[]{1, 2, 3, 4, 5});
        exp = 4;
        res = sumOfLeftLeaves(root);
        Assert.assertEquals(exp, res);

        root = TreeNode.createTree(new Integer[]{-9, -3, 2, null, 4, 4, 0, -6, null, -5});
        exp = -11;
        res = sumOfLeftLeaves(root);
        Assert.assertEquals(exp, res);

    }

    public int sumOfLeftLeaves(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null) {
            return sumOfLeftLeaves(root.right);
        }
        int rightSum = sumOfLeftLeaves(root.right);
        int leftSum = sumOfLeftLeaves(root.left);
        int sum = leftSum + rightSum;
        // root.left是叶子结点才需要加上
        if (root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        }
        return sum;
    }
}
