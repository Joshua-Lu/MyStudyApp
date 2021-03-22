package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by Joshua on 2021/3/22.
 */
public class AllPathSum {
    /*
    https://leetcode-cn.com/problems/path-sum-ii/
    113. 路径总和 II
    给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。

    叶子节点 是指没有子节点的节点。

    示例 1：
        输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
        输出：[[5,4,11,2],[5,8,4,5]]
    示例 2：
        输入：root = [1,2,3], targetSum = 5
        输出：[]
    示例 3：
        输入：root = [1,2], targetSum = 0
        输出：[]*/

    @Test
    public void test() {
        TreeNode root;
        int targetSum;
        List<List<Integer>> exp, res;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1});
        targetSum = 22;
        exp = Arrays.asList(Arrays.asList(5, 4, 11, 2), Arrays.asList(5, 8, 4, 5));
        res = pathSum(root, targetSum);
        Assert.assertEquals(exp, res);

        // 示例 2
        root = TreeNode.createTree(new Integer[]{1, 2, 3});
        targetSum = 5;
        exp = Arrays.asList();
        res = pathSum(root, targetSum);
        Assert.assertEquals(exp, res);

        // 示例 3
        root = TreeNode.createTree(new Integer[]{1, 2});
        targetSum = 0;
        exp = Arrays.asList();
        res = pathSum(root, targetSum);
        Assert.assertEquals(exp, res);

    }

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> path = new ArrayList<>();
//        pathSum1(root, targetSum, path, res);
        pathSum2(root, targetSum, path, res);
        return res;
    }

    /**
     * 递归法
     */
    public void pathSum1(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> res) {
        path.add(root.val);
        targetSum -= root.val;
        if (root.left == null && root.right == null) {
            if (targetSum == 0) {// 找到一条路径
                res.add(new ArrayList<>(path));
            }
            return;
        }
        if (root.left != null) {
            pathSum1(root.left, targetSum, path, res);
            path.remove(path.size() - 1);// 回溯
        }
        if (root.right != null) {
            pathSum1(root.right, targetSum, path, res);
            path.remove(path.size() - 1);// 回溯
        }
    }

    /**
     * 迭代法，采用后序遍历
     */
    public void pathSum2(TreeNode root, int targetSum, List<Integer> path, List<List<Integer>> res) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                path.add(root.val);
                targetSum -= root.val;
                root = root.left;
            }
            TreeNode peek = stack.peek();
            System.out.println("AllPathSum.pathSum2: peek.val = [" + peek.val + "]");
            if (peek.right == null || peek.right == prev) {
                // 处理
                if (peek.left == null && peek.right == null) {
                    if (targetSum == 0) {// 找到一条路径
                        res.add(new ArrayList<>(path));
                    }
                }
                path.remove(path.size() - 1);
                targetSum += peek.val;
                stack.pop();
                prev = peek;
            } else {
                root = peek.right;
            }
        }
    }
}
