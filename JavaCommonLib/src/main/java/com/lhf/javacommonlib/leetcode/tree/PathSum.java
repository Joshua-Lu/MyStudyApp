package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/22.
 */
public class PathSum {
    /*
    https://leetcode-cn.com/problems/path-sum/
    112. 路径总和
    给你二叉树的根节点 root 和一个表示目标和的整数 targetSum ，判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。

    叶子节点 是指没有子节点的节点。

    示例 1：
        输入：root = [5,4,8,11,null,13,4,7,2,null,null,null,1], targetSum = 22
        输出：true
    示例 2：
        输入：root = [1,2,3], targetSum = 5
        输出：false
    示例 3：
        输入：root = [1,2], targetSum = 0
        输出：false


    提示：

    树中节点的数目在范围 [0, 5000] 内
    -1000 <= Node.val <= 1000
    -1000 <= targetSum <= 1000*/

    @Test
    public void test() {
        TreeNode root;
        int targetSum;
        boolean exp;
        boolean res;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        targetSum = 22;
        exp = true;
        res = hasPathSum(root, targetSum);
        Assert.assertEquals(exp, res);

        // 示例 2
        root = TreeNode.createTree(new Integer[]{1, 2, 3});
        targetSum = 5;
        exp = false;
        res = hasPathSum(root, targetSum);
        Assert.assertEquals(exp, res);

        // 示例 3
        root = TreeNode.createTree(new Integer[]{1, 2});
        targetSum = 0;
        exp = false;
        res = hasPathSum(root, targetSum);
        Assert.assertEquals(exp, res);

        // 示例 4
        root = TreeNode.createTree(new Integer[]{0, 1, 1});
        targetSum = 0;
        exp = false;
        res = hasPathSum(root, targetSum);
        Assert.assertEquals(exp, res);

    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        targetSum -= root.val;
        // 叶子结点
        if (root.left == null && root.right == null) {
            return targetSum == 0;
        }
        if (root.left != null) {
            boolean left = hasPathSum(root.left, targetSum);
            if (left) {// 已经找到
                return true;
            }
        }
        if (root.right != null) {
            boolean right = hasPathSum(root.right, targetSum);
            if (right) {// 已经找到
                return true;
            }
        }
        return false;
    }
}
