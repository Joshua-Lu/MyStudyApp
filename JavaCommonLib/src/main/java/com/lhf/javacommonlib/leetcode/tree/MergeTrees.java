package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by Joshua on 2021/3/23.
 */
public class MergeTrees {
    /*
    https://leetcode-cn.com/problems/merge-two-binary-trees/
    617. 合并二叉树
    给定两个二叉树，想象当你将它们中的一个覆盖到另一个上时，两个二叉树的一些节点便会重叠。

    你需要将他们合并为一个新的二叉树。合并的规则是如果两个节点重叠，那么将他们的值相加作为节点合并后的新值，否则不为 NULL 的节点将直接作为新二叉树的节点。

    示例 1:

    输入:
    Tree 1                     Tree 2
              1                         2
             / \                       / \
            3   2                     1   3
           /                           \   \
          5                             4   7
    输出:
    合并后的树:
              3
             / \
            4   5
           / \   \
          5   4   7
    注意: 合并必须从两个树的根节点开始。*/

    @Test
    public void test() {
        TreeNode root1, root2;
        TreeNode exp, res;

        root1 = TreeNode.createTree(new Integer[]{1, 3, 2, 5});
        root2 = TreeNode.createTree(new Integer[]{2, 1, 3, null, 4, null, 7});
        exp = TreeNode.createTree(new Integer[]{3, 4, 5, 5, 4, null, 7});
        res = mergeTrees(root1, root2);
        Assert.assertEquals(exp.toString(), res.toString());
    }

    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
//        return mergeTrees1(root1, root2);
        return mergeTrees2(root1, root2);
    }

    // 递归
    public TreeNode mergeTrees1(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        root1.val += root2.val;
        root1.left = mergeTrees1(root1.left, root2.left);
        root1.right = mergeTrees1(root1.right, root2.right);
        return root1;
    }

    // 迭代
    public TreeNode mergeTrees2(TreeNode root1, TreeNode root2) {
        if (root1 == null) {
            return root2;
        }
        if (root2 == null) {
            return root1;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root2);
        stack.push(root1);
        while (!stack.isEmpty()) {
            TreeNode node1 = stack.pop();
            TreeNode node2 = stack.pop();
            node1.val += node2.val;
            if (node2.right != null && node1.right != null) {
                stack.push(node2.right);
                stack.push(node1.right);
            } else {
                if (node1.right == null) {
                    node1.right = node2.right;
                }
            }
            if (node2.left != null && node1.left != null) {
                stack.push(node2.left);
                stack.push(node1.left);
            } else {
                if (node1.left == null) {
                    node1.left = node2.left;
                }
            }
        }
        return root1;
    }
}
