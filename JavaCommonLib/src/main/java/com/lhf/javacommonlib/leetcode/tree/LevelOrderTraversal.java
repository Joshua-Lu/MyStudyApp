package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * 二叉树的层序遍历
 * <p>
 * Created by Joshua on 2021/3/15.
 */
public class LevelOrderTraversal {
    /*
    https://leetcode-cn.com/problems/binary-tree-level-order-traversal/
    102. 二叉树的层序遍历
    给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。

    示例：
        二叉树：[3,9,20,null,null,15,7],

                 3
                / \
               9   20
                  /  \
                 15   7
        返回其层序遍历结果：
                [
                [3],
                [9,20],
                [15,7]
                ]
    */

    @Test
    public void test() {
        TreeNode root;
        List<List<Integer>> expected;
        List<List<Integer>> result;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        expected = Arrays.asList(
                Arrays.asList(3),
                Arrays.asList(9, 20),
                Arrays.asList(15, 7));
        result = levelOrder(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 2
        root = TreeNode.createTree(new Integer[]{});
        expected = Arrays.asList();
        result = levelOrder(root);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(root);
        while (!deque.isEmpty()) {
            int size = deque.size();// 当前这一层有多少个结点
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll != null) {
                    list.add(poll.val);
                    if (poll.left != null) {
                        deque.offer(poll.left);
                    }
                    if (poll.right != null) {
                        deque.offer(poll.right);
                    }
                }
            }
            result.add(list);
        }
        System.out.println("LevelOrderTraversal.levelOrder returned: result = [" + Arrays.toString(result.toArray()) + "]");
        return result;
    }
}
