package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树前序遍历
 * <p>
 * Created by Joshua on 2021/3/13 13:48
 */
public class PreorderTraversal {
    /*
    https://leetcode-cn.com/problems/binary-tree-preorder-traversal/
    144. 二叉树的前序遍历
    给定一个二叉树的根节点 root ，返回它的 前序 遍历。

    示例 1：
        输入：root = [1,null,2,3]
        输出：[1,2,3]
    示例 2：
        输入：root = []
        输出：[]
    示例 3：
        输入：root = [1]
        输出：[1]
    示例 4：
        输入：root = [1,2]
        输出：[1,2]
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
        expected = Arrays.asList(1, 2, 3);
        result = preorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 2
        root = TreeNode.createTree(new Integer[]{1});
        expected = Arrays.asList(1);
        result = preorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 3
        root = TreeNode.createTree(new Integer[]{});
        expected = Arrays.asList();
        result = preorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 4
        root = TreeNode.createTree(new Integer[]{1, 2});
        expected = Arrays.asList(1, 2);
        result = preorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例
        root = TreeNode.createTree(new Integer[]{1, null, 2});
        expected = Arrays.asList(1, 2);
        result = preorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

    }

    public List<Integer> preorderTraversal(TreeNode root) {
//        return preorderTraversal1(root);
        return preorderTraversal2(root);
    }

    /**
     * 递归方式
     */
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        preorderTraversal1(root, result);
        return result;
    }

    private void preorderTraversal1(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        result.add(root.val);// 先后顺序决定先序中序后序遍历
        preorderTraversal1(root.left, result);
        preorderTraversal1(root.right, result);
    }

    /**
     * 迭代方式，通过Stack实现
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            // 输出
            result.add(pop.val);
            // 先push右节点，再push左节点，pop时才是左右的顺序
            if (pop.right != null) {
                stack.push(pop.right);
            }
            if (pop.left != null) {
                stack.push(pop.left);
            }
        }
        return result;
    }
}
