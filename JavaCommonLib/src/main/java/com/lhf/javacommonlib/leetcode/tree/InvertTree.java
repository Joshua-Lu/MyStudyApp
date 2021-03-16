package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/15.
 */
public class InvertTree {
    /*
    https://leetcode-cn.com/problems/invert-binary-tree/
    226. 翻转二叉树
    翻转一棵二叉树。

    示例：

    输入：

                 4
               /   \
              2     7
             / \   / \
            1   3 6   9
    输出：

                  4
                /   \
               7     2
              / \   / \
             9   6 3   1
    备注:
    这个问题是受到 Max Howell 的 原问题 启发的 ：
        谷歌：我们90％的工程师使用您编写的软件(Homebrew)，但是您却无法在面试时在白板上写出翻转二叉树这道题，这太糟糕了。
    */

    @Test
    public void test() {
        TreeNode root;
        TreeNode expected;
        TreeNode result;

        root = TreeNode.createTree(new Integer[]{4, 2, 7, 1, 3, 6, 9});
        expected = TreeNode.createTree(new Integer[]{4, 7, 2, 9, 6, 3, 1});
        result = invertTree(root);
        if (expected != null && result != null) {
            Assert.assertEquals(expected.toString(), result.toString());
        } else {
            Assert.assertNull(expected);
            Assert.assertNull(result);
        }
    }

    /**
     * 前后序遍历都可以
     * 中序不行，因为先左子树交换孩子，再根交换孩子（做完后，右子树已经变成了原来的左子树），再右子树交换孩子（此时其实是对原来的左子树做交换）
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        invertTree(root.left);
        invertTree(root.right);
        swapChildren(root);
        return root;
    }

    private void swapChildren(TreeNode root) {
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
    }
}
