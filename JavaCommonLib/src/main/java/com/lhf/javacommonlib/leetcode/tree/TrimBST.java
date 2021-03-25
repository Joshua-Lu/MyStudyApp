package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/25.
 */
public class TrimBST {
    /*
    https://leetcode-cn.com/problems/trim-a-binary-search-tree/
    669. 修剪二叉搜索树
    给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。修剪树不应该改变保留在树中的元素的相对结构（即，如果没有被移除，原有的父代子代关系都应当保留）。 可以证明，存在唯一的答案。

    所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。

    示例 1：
        输入：root = [1,0,2], low = 1, high = 2
        输出：[1,null,2]
    示例 2：
        输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
        输出：[3,2,null,1]
    示例 3：
        输入：root = [1], low = 1, high = 2
        输出：[1]
    示例 4：
        输入：root = [1,null,2], low = 1, high = 3
        输出：[1,null,2]
    示例 5：
        输入：root = [1,null,2], low = 2, high = 4
        输出：[2]

    提示：
        树中节点数在范围 [1, 104] 内
        0 <= Node.val <= 104
        树中每个节点的值都是唯一的
        题目数据保证输入是一棵有效的二叉搜索树
        0 <= low <= high <= 104*/

    @Test
    public void test() {
        TreeNode root;
        int low;
        int high;
        TreeNode exp, res;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{1, 0, 2});
        low = 1;
        high = 2;
        exp = TreeNode.createTree(new Integer[]{1, null, 2});
        res = trimBST(root, low, high);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

        // 示例 2
        root = TreeNode.createTree(new Integer[]{3, 0, 4, null, 2, null, null, 1});
        low = 1;
        high = 3;
        exp = TreeNode.createTree(new Integer[]{3, 2, null, 1});
        res = trimBST(root, low, high);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

        // 示例 3
        root = TreeNode.createTree(new Integer[]{1});
        low = 1;
        high = 2;
        exp = TreeNode.createTree(new Integer[]{1});
        res = trimBST(root, low, high);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

        // 示例 4
        root = TreeNode.createTree(new Integer[]{1, null, 2});
        low = 1;
        high = 3;
        exp = TreeNode.createTree(new Integer[]{1, null, 2});
        res = trimBST(root, low, high);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

        // 示例 5
        root = TreeNode.createTree(new Integer[]{1, null, 2});
        low = 2;
        high = 4;
        exp = TreeNode.createTree(new Integer[]{2});
        res = trimBST(root, low, high);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

        // 示例 6
        root = TreeNode.createTree(new Integer[]{3, 1, 4, null, 2});
        low = 3;
        high = 4;
        exp = TreeNode.createTree(new Integer[]{3, null, 4});
        res = trimBST(root, low, high);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

        // 示例 7
        root = TreeNode.createTree(new Integer[]{28, 12, 45, 4, 24, 35, 47, 2, 9, 14, 25, 31, 42,
                46, 48, 0, 3, 8, 11, 13, 20, null, 26, 30, 33, 41, 43, null, null, null, 49, null,
                1, null, null, 7, null, 10, null, null, null, 17, 22, null, 27, 29, null, 32, 34,
                36, null, null, 44, null, null, null, null, 6, null, null, null, 16, 18, 21, 23,
                null, null, null, null, null, null, null, null, null, 37, null, null, 5, null, 15,
                null, null, 19, null, null, null, null, null, 40, null, null, null, null, null,
                null, 39, null, 38});
        low = 12;
        high = 22;
        exp = TreeNode.createTree(new Integer[]{12, null, 14, 13, 20, null, null, 17, 22, 16, 18,
                21, null, 15, null, null, 19});
        res = trimBST(root, low, high);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());


    }

    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        // root在[low,high]范围内
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        return root;
    }
}
