package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/18.
 */
public class SameTree {
    /*
    https://leetcode-cn.com/problems/same-tree/
    100. 相同的树
    给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。

    如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。

    示例 1：
        输入：p = [1,2,3], q = [1,2,3]
        输出：true
    示例 2：
        输入：p = [1,2], q = [1,null,2]
        输出：false
    示例 3：
        输入：p = [1,2,1], q = [1,1,2]
        输出：false

    提示：

    两棵树上的节点数目都在范围 [0, 100] 内
    -104 <= Node.val <= 104
    */

    @Test
    public void test() {
        TreeNode p;
        TreeNode q;

        // 示例 1
        p = TreeNode.createTree(new Integer[]{1, 2, 3});
        q = TreeNode.createTree(new Integer[]{1, 2, 3});
        Assert.assertTrue(isSameTree(p, q));

        // 示例 2
        p = TreeNode.createTree(new Integer[]{1, 2});
        q = TreeNode.createTree(new Integer[]{1, null, 2});
        Assert.assertFalse(isSameTree(p, q));

        // 示例 3
        p = TreeNode.createTree(new Integer[]{1, 2, 1});
        q = TreeNode.createTree(new Integer[]{1, 1, 2});
        Assert.assertFalse(isSameTree(p, q));

    }

    /**
     * 递归法
     */
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q != null) return false;
        if (p != null && q == null) return false;
        if (p == null && q == null) return true;
        if (p.val != q.val) return false;
        boolean leftSame = isSameTree(p.left, q.left);
        boolean rightSame = isSameTree(p.right, q.right);
        return leftSame && rightSame;
    }
}
