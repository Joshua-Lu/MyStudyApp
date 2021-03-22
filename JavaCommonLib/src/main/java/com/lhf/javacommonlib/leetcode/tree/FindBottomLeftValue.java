package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Joshua on 2021/3/22.
 */
public class FindBottomLeftValue {
    /*
    https://leetcode-cn.com/problems/find-bottom-left-tree-value/
    513. 找树左下角的值
    给定一个二叉树，在树的最后一行找到最左边的值。

    示例 1:

    输入:

             2
            / \
           1   3

    输出:
            1


    示例 2:

    输入:

               1
              / \
             2   3
            /   / \
           4   5   6
              /
             7

    输出:
            7


    注意: 您可以假设树（即给定的根节点）不为 NULL。*/

    @Test
    public void test() {

        TreeNode root;
        int exp;
        int res;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{2, 1, 3});
        exp = 1;
        res = findBottomLeftValue(root);
        Assert.assertEquals(exp, res);

        // 示例 2
        root = TreeNode.createTree(new Integer[]{1, 2, 3, 4, null, 5, 6, null, null, 7});
        exp = 7;
        res = findBottomLeftValue(root);
        Assert.assertEquals(exp, res);

    }

    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int res = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (i == 0) {
                    res = poll.val;
                }
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return res;
    }
}
