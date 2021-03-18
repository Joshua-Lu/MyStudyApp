package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by Joshua on 2021/3/18.
 */
public class BinaryTreePaths {
    /*
    https://leetcode-cn.com/problems/binary-tree-paths/
    257. 二叉树的所有路径
    给定一个二叉树，返回所有从根节点到叶子节点的路径。

    说明: 叶子节点是指没有子节点的节点。

    示例:

    输入:

              1
            /   \
           2     3
            \
            5

    输出: ["1->2->5", "1->3"]

    解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
    */

    @Test
    public void test() {
        TreeNode root = TreeNode.createTree(new Integer[]{1, 2, 3, null, 5});
        List<String> exp = Arrays.asList("1->2->5", "1->3");
        List<String> res = binaryTreePaths(root);
        Assert.assertArrayEquals(exp.toArray(), res.toArray());
    }

    public List<String> binaryTreePaths(TreeNode root) {
//        return binaryTreePaths1(root);
        return binaryTreePaths2(root);
    }

    /**
     * 递归法
     * <p>
     * paths用List实现
     */
    private List<String> binaryTreePaths1(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        List<Integer> paths = new ArrayList<>();
        traversal1(root, paths, res);
        return res;
    }

    private void traversal1(TreeNode root, List<Integer> paths, List<String> res) {
        paths.add(root.val);
        // 叶子结点
        if (root.left == null && root.right == null) {
            // 输出
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paths.size() - 1; i++) {
                sb.append(paths.get(i)).append("->");

            }
            sb.append(paths.get(paths.size() - 1));
            res.add(sb.toString());
            return;
        }
        if (root.left != null) {
            traversal1(root.left, paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }
        if (root.right != null) {
            traversal1(root.right, paths, res);
            paths.remove(paths.size() - 1);// 回溯
        }
    }

    /**
     * 递归法
     * <p>
     * paths也可以用Stack实现
     */
    public List<String> binaryTreePaths2(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<String> paths = new Stack<>();
        traversal2(root, paths, res);
        return res;
    }

    private void traversal2(TreeNode root, Stack<String> paths, List<String> res) {
        if (paths.empty()) {
            paths.push(String.valueOf(root.val));
        } else {
            paths.push(paths.peek() + "->" + root.val);
        }
        // 叶子结点
        if (root.left == null && root.right == null) {
            // 输出
            res.add(paths.peek());
            return;
        }
        if (root.left != null) {
            traversal2(root.left, paths, res);
            paths.pop();// 回溯
        }
        if (root.right != null) {
            traversal2(root.right, paths, res);
            paths.pop();// 回溯
        }
    }
}
