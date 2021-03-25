package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/25.
 */
public class ConvertBST {
    /*
    https://leetcode-cn.com/problems/convert-bst-to-greater-tree/
    538. 把二叉搜索树转换为累加树
    给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

    提醒一下，二叉搜索树满足下列约束条件：

    节点的左子树仅包含键 小于 节点键的节点。
    节点的右子树仅包含键 大于 节点键的节点。
    左右子树也必须是二叉搜索树。
    注意：本题和 1038: https://leetcode-cn.com/problems/binary-search-tree-to-greater-sum-tree/ 相同

    示例 1：
        输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
        输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
    示例 2：
        输入：root = [0,null,1]
        输出：[1,null,1]
    示例 3：
        输入：root = [1,0,2]
        输出：[3,3,2]
    示例 4：
        输入：root = [3,2,4,1]
        输出：[7,9,4,10]


    提示：
        树中的节点数介于 0 和 104 之间。
        每个节点的值介于 -104 和 104 之间。
        树中的所有值 互不相同 。
        给定的树为二叉搜索树。*/

    @Test
    public void test() {
        TreeNode root;
        TreeNode exp, res;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{4, 1, 6, 0, 2, 5, 7, null, null, null, 3, null, null, null, 8});
        exp = TreeNode.createTree(new Integer[]{30, 36, 21, 36, 35, 26, 15, null, null, null, 33, null, null, null, 8});
        res = convertBST(root);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

        // 示例 2
        root = TreeNode.createTree(new Integer[]{0, null, 1});
        exp = TreeNode.createTree(new Integer[]{1, null, 1});
        res = convertBST(root);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

        // 示例 3
        root = TreeNode.createTree(new Integer[]{1, 0, 2});
        exp = TreeNode.createTree(new Integer[]{3, 3, 2});
        res = convertBST(root);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());

        // 示例
        root = TreeNode.createTree(new Integer[]{3, 2, 4, 1});
        exp = TreeNode.createTree(new Integer[]{7, 9, 4, 10});
        res = convertBST(root);
        Assert.assertEquals(exp.toArrayString(), res.toArrayString());


    }

    int sum;

    public TreeNode convertBST(TreeNode root) {
        sum = 0;
        convertBST1(root);
        return root;
    }

    // 按右中左顺序遍历，累加即可
    public void convertBST1(TreeNode root) {
        if (root == null) {
            return;
        }
        convertBST1(root.right);
        sum += root.val;
        root.val = sum;
        convertBST1(root.left);
    }
}
