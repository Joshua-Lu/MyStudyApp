package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by Joshua on 2021/3/23.
 */
public class SearchBST {
    /*
    https://leetcode-cn.com/problems/search-in-a-binary-search-tree/
    700. 二叉搜索树中的搜索
    给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。

    例如，

    给定二叉搜索树:

              4
             / \
            2   7
           / \
          1   3

    和值: 2
    你应该返回如下子树:

             2
            / \
           1   3
    在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。*/

    @Test
    public void test() {
        TreeNode root;
        int val;
        TreeNode exp, res;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{4, 2, 7, 1, 3});
        val = 2;
        exp = TreeNode.createTree(new Integer[]{2, 1, 3});
        res = searchBST(root, val);
        Assert.assertEquals(exp.toString(), res.toString());

        // 示例 2
        root = TreeNode.createTree(new Integer[]{4, 2, 7, 1, 3});
        val = 5;
        exp = null;
        res = searchBST(root, val);
        Assert.assertEquals(exp, res);

    }

    public TreeNode searchBST(TreeNode root, int val) {
//        return searchBST1(root, val);
//        return searchBST1Optimize(root, val);
//        return searchBST2(root, val);
        return searchBST2Optimize(root, val);
    }

    // 递归
    public TreeNode searchBST1(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        TreeNode left = searchBST1(root.left, val);
        if (left != null) {
            return left;
        }
        return searchBST1(root.right, val);
    }

    // 递归，利用二叉搜索树特点，优化
    public TreeNode searchBST1Optimize(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        if (val < root.val) {
            return searchBST1Optimize(root.left, val);
        } else {
            return searchBST1Optimize(root.right, val);
        }
    }

    // 迭代
    public TreeNode searchBST2(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            if (pop.val == val) {
                return pop;
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return null;
    }

    // 迭代，利用二叉搜索树特点，优化，可以不需要栈
    public TreeNode searchBST2Optimize(TreeNode root, int val) {
        if (root == null || root.val == val) {
            return root;
        }
        while (root != null) {
            if (root.val == val) {
                return root;
            } else if (val < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return null;
    }
}
