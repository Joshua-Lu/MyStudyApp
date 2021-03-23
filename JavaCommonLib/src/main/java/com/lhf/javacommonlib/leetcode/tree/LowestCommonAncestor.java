package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/23.
 */
public class LowestCommonAncestor {
    /*
    https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/
    236. 二叉树的最近公共祖先
    给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

    百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

    示例 1：
        输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        输出：3
        解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
    示例 2：
        输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
        输出：5
        解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
    示例 3：
        输入：root = [1,2], p = 1, q = 2
        输出：1

    提示：
        树中节点数目在范围 [2, 105] 内。
        -109 <= Node.val <= 109
        所有 Node.val 互不相同 。
        p != q
        p 和 q 均存在于给定的二叉树中。*/

    @Test
    public void test() {
        TreeNode root, p, q;
        TreeNode exp, res;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        p = root.left;
        q = root.right;
        exp = root;
        res = lowestCommonAncestor(root, p, q);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

        // 示例 2
        root = TreeNode.createTree(new Integer[]{3, 5, 1, 6, 2, 0, 8, null, null, 7, 4});
        p = root.left;
        q = root.left.right.right;
        exp = root.left;
        res = lowestCommonAncestor(root, p, q);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

        // 示例 1
        root = TreeNode.createTree(new Integer[]{1, 2});
        p = root;
        q = root.left;
        exp = root;
        res = lowestCommonAncestor(root, p, q);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        System.out.println("LowestCommonAncestor.lowestCommonAncestor() called with: root = [" + root + "], p = [" + p.val + "], q = [" + q.val + "]");
        return lowestCommonAncestor1(root, p, q);

    }

    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor1(root.left, p, q);
        TreeNode right = lowestCommonAncestor1(root.right, p, q);
        if (left != null && right != null) {// 左右子树分别找到了，说明此时的root就是要求的结果
            return root;
        }
        if (left == null) {
            return right;
        }
        return left;
    }
}
