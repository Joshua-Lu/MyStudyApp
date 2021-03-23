package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/23.
 */
public class MinimumDifference {
    /*
    https://leetcode-cn.com/problems/minimum-absolute-difference-in-bst/
    530. 二叉搜索树的最小绝对差
    给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。

    示例：
        输入：

           1
            \
             3
            /
           2

        输出：
            1

    解释：
    最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。

    提示：
    树中至少有 2 个节点。
    本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 相同*/

    @Test
    public void test() {
        TreeNode root;
        int exp, res;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{1, null, 3, 2});
        exp = 1;
        res = getMinimumDifference(root);
        Assert.assertEquals(exp, res);

        // 示例 2
        root = TreeNode.createTree(new Integer[]{5, 4, 7});
        exp = 1;
        res = getMinimumDifference(root);
        Assert.assertEquals(exp, res);

    }

    TreeNode pre;// 记录上一个遍历的结点
    int result = Integer.MAX_VALUE;

    public int getMinimumDifference(TreeNode root) {
        pre = null;
        result = Integer.MAX_VALUE;
        getMinimumDifference1(root);
        return result;
    }

    private void getMinimumDifference1(TreeNode root) {
        if (root == null) {
            return;
        }
        // 左
        getMinimumDifference1(root.left);

        // 中
        if (pre != null) {
            result = Math.min(result, root.val - pre.val);
        }
        pre = root;
        // 右
        getMinimumDifference1(root.right);
    }
}
