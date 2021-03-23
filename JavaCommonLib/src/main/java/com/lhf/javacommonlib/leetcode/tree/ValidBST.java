package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by Joshua on 2021/3/23.
 */
public class ValidBST {
    /*
    https://leetcode-cn.com/problems/validate-binary-search-tree/
    98. 验证二叉搜索树
    给定一个二叉树，判断其是否是一个有效的二叉搜索树。

    假设一个二叉搜索树具有如下特征：

    节点的左子树只包含小于当前节点的数。
    节点的右子树只包含大于当前节点的数。
    所有左子树和右子树自身必须也是二叉搜索树。
    示例 1:

    输入:
             2
            / \
           1   3
    输出: true
    示例 2:

    输入:
              5
             / \
            1   4
               / \
              3   6
    输出: false
    解释: 输入为: [5,1,4,null,null,3,6]。
    根节点的值为 5 ，但是其右子节点值为 4 。*/

    @Test
    public void test() {
        TreeNode root;
        boolean exp, res;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{2, 1, 3});
        exp = true;
        res = isValidBST(root);
        Assert.assertEquals(exp, res);

        // 示例 2
        root = TreeNode.createTree(new Integer[]{5, 1, 4, null, null, 3, 6});
        exp = false;
        res = isValidBST(root);
        Assert.assertEquals(exp, res);

    }

    TreeNode pre;// 记录上一个遍历的结点

    public boolean isValidBST(TreeNode root) {
//        return isValidBST1(root);
        return isValidBST2(root);
    }

    // 递归
    public boolean isValidBST1(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 左
        boolean left = isValidBST1(root.left);
        if (!left) {
            return false;
        }
        // 中
        if (pre != null && root.val <= pre.val) {
            return false;
        }
        pre = root;
        // 右
        boolean right = isValidBST1(root.right);
        return right;
    }

    // 迭代
    public boolean isValidBST2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;// 左
            }
            // 中，处理
            TreeNode pop = stack.pop();
            if (pre != null && pop.val <= pre.val) {
                return false;
            }
            pre = pop;

            root = pop.right;// 右
        }
        return true;
    }
}
