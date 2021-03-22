package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/22.
 */
public class BuildTree {
    /*
    https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
    106. 从中序与后序遍历序列构造二叉树
    根据一棵树的中序遍历与后序遍历构造二叉树。

    注意:
    你可以假设树中没有重复的元素。

    例如，给出

    中序遍历 inorder = [9,3,15,20,7]
    后序遍历 postorder = [9,15,7,20,3]
    返回如下的二叉树：

              3
             / \
            9  20
              /  \
             15   7
    */

    @Test
    public void test() {
        int[] inorder, postorder;
        TreeNode exp, res;

        // 示例 1
        inorder = new int[]{9, 3, 15, 20, 7};
        postorder = new int[]{9, 15, 7, 20, 3};
        exp = TreeNode.createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        res = buildTree(inorder, postorder);
        Assert.assertEquals(exp.toString(), res.toString());

        // 示例 2
        inorder = new int[]{2, 1};
        postorder = new int[]{2, 1};
        exp = TreeNode.createTree(new Integer[]{1, 2, null});
        res = buildTree(inorder, postorder);
        Assert.assertEquals(exp.toString(), res.toString());

    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return buildTree1(inorder, 0, inorder.length, postorder, 0, postorder.length);
    }

    public TreeNode buildTree1(int[] inorder, int inLeft, int inRight,
                               int[] postorder, int postLeft, int postRight) {
        // 没有元素了
        if (inRight - inLeft < 1) {
            return null;
        }
        // 只有一个元素了
        if (inRight - inLeft == 1) {
            return new TreeNode(inorder[inLeft]);
        }
        // 后序数组postorder里最后一个即为根结点
        int rootVal = postorder[postRight - 1];
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = 0;
        // 根据根结点的值找到该值在中序数组inorder里的位置
        for (int i = inLeft; i < inRight; i++) {
            if (inorder[i] == rootVal) {
                rootIndex = i;
            }
        }
        // 根据rootIndex划分左右子树
        root.left = buildTree1(inorder, inLeft, rootIndex,
                postorder, postLeft, postLeft + (rootIndex - inLeft));
        root.right = buildTree1(inorder, rootIndex + 1, inRight,
                postorder, postLeft + (rootIndex - inLeft), postRight - 1);
        return root;
    }
}
