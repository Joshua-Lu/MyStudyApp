package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joshua on 2021/3/11 22:59
 */
public class InorderTraversal {
    /*
    https://leetcode-cn.com/problems/binary-tree-inorder-traversal/
    94. 二叉树的中序遍历
    给定一个二叉树的根节点 root ，返回它的 中序 遍历。

    示例 1：
        输入：root = [1,null,2,3]
        输出：[1,3,2]
    示例 2：
        输入：root = []
        输出：[]
    示例 3：
        输入：root = [1]
        输出：[1]
    示例 4：
        输入：root = [1,2]
        输出：[2,1]
    示例 5：
        输入：root = [1,null,2]
        输出：[1,2]

    提示：

        树中节点数目在范围 [0, 100] 内
        -100 <= Node.val <= 100

    进阶:
        递归算法很简单，你可以通过迭代算法完成吗？*/

    @Test
    public void test() {
        TreeNode root;
        List<Integer> expected;
        List<Integer> result;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{1, null, 2, 3});
        expected = Arrays.asList(1, 3, 2);
        result = inorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 2
        root = TreeNode.createTree(new Integer[]{1});
        expected = Arrays.asList(1);
        result = inorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 3
        root = TreeNode.createTree(new Integer[]{});
        expected = Arrays.asList();
        result = inorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 4
        root = TreeNode.createTree(new Integer[]{1, 2});
        expected = Arrays.asList(2, 1);
        result = inorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例
        root = TreeNode.createTree(new Integer[]{1, null, 2});
        expected = Arrays.asList(1, 2);
        result = inorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        return inorderTraversal1(root);
    }

    /**
     * 递归方式
     */
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal1(root, result);
        return result;
    }

    private void inorderTraversal1(TreeNode root, List<Integer> result) {
        if (root == null || root.val == null) {
            return;
        }
        inorderTraversal1(root.left, result);
        result.add(root.val);// 先后顺序决定先序中序后序遍历
        inorderTraversal1(root.right, result);
    }
}
