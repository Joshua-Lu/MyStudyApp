package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树后序遍历
 * <p>
 * Created by Joshua on 2021/3/13 13:48
 */
public class PostorderTraversal {
    /*
    https://leetcode-cn.com/problems/binary-tree-postorder-traversal/
    144. 二叉树的后序遍历
    给定一个二叉树的根节点 root ，返回它的 后序 遍历。

    示例 1：
        输入：root = [1,null,2,3]
        输出：[3,2,1]
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
        输出：[2,1]

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
        expected = Arrays.asList(3, 2, 1);
        result = postorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 2
        root = TreeNode.createTree(new Integer[]{1});
        expected = Arrays.asList(1);
        result = postorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 3
        root = TreeNode.createTree(new Integer[]{});
        expected = Arrays.asList();
        result = postorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 4
        root = TreeNode.createTree(new Integer[]{1, 2});
        expected = Arrays.asList(2, 1);
        result = postorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例
        root = TreeNode.createTree(new Integer[]{1, null, 2});
        expected = Arrays.asList(2, 1);
        result = postorderTraversal(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

    }

    public List<Integer> postorderTraversal(TreeNode root) {
//        return postorderTraversal1(root);
        return postorderTraversal2(root);
    }

    /**
     * 递归方式
     */
    public List<Integer> postorderTraversal1(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        postorderTraversal1(root, result);
        return result;
    }

    private void postorderTraversal1(TreeNode root, List<Integer> result) {
        if (root == null) {
            return;
        }
        postorderTraversal1(root.left, result);
        postorderTraversal1(root.right, result);
        result.add(root.val);// 先后顺序决定先序中序后序遍历
    }

    /**
     * 迭代方式，通过Stack实现
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            // 输出，插入在尾部的话顺序就是中右左，现在插在头部，则最后输出为左右中
            result.add(0, pop.val);
            // 先push左节点，再push右节点，pop时是右左的顺序
            if (pop.left != null) {
                stack.push(pop.left);
            }
            if (pop.right != null) {
                stack.push(pop.right);
            }
        }
        return result;
    }
}
