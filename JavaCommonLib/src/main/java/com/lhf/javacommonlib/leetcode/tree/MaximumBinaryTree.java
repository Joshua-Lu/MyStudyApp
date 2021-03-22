package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/22.
 */
public class MaximumBinaryTree {
    /*
    https://leetcode-cn.com/problems/maximum-binary-tree/
    654. 最大二叉树
    给定一个不含重复元素的整数数组 nums 。一个以此数组直接递归构建的 最大二叉树 定义如下：

    二叉树的根是数组 nums 中的最大元素。
    左子树是通过数组中 最大值左边部分 递归构造出的最大二叉树。
    右子树是通过数组中 最大值右边部分 递归构造出的最大二叉树。
    返回有给定数组 nums 构建的 最大二叉树 。

    示例 1：

    输入：nums = [3,2,1,6,0,5]
        输出：[6,3,5,null,2,0,null,null,1]
        解释：递归调用如下所示：
        - [3,2,1,6,0,5] 中的最大值是 6 ，左边部分是 [3,2,1] ，右边部分是 [0,5] 。
            - [3,2,1] 中的最大值是 3 ，左边部分是 [] ，右边部分是 [2,1] 。
                - 空数组，无子节点。
                - [2,1] 中的最大值是 2 ，左边部分是 [] ，右边部分是 [1] 。
                    - 空数组，无子节点。
                    - 只有一个元素，所以子节点是一个值为 1 的节点。
            - [0,5] 中的最大值是 5 ，左边部分是 [0] ，右边部分是 [] 。
                - 只有一个元素，所以子节点是一个值为 0 的节点。
                - 空数组，无子节点。
    示例 2：
        输入：nums = [3,2,1]
        输出：[3,null,2,null,1]
    */

    @Test
    public void test() {
        int[] nums;
        TreeNode exp, res;

        // 示例 1
        nums = new int[]{3, 2, 1};
        exp = TreeNode.createTree(new Integer[]{3, null, 2, null, 1});
        res = constructMaximumBinaryTree(nums);
        Assert.assertEquals(exp.toString(), res.toString());

        // 示例 2
        nums = new int[]{3, 2, 1, 6, 0, 5};
        exp = TreeNode.createTree(new Integer[]{6, 3, 5, null, 2, 0, null, null, 1});
        res = constructMaximumBinaryTree(nums);
        Assert.assertEquals(exp.toString(), res.toString());

    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree1(nums, 0, nums.length);
    }

    public TreeNode constructMaximumBinaryTree1(int[] nums, int leftIndex, int rightIndex) {
        if (rightIndex - leftIndex < 1) {// 没有元素了
            return null;
        }
        if (rightIndex - leftIndex == 1) {// 只有一个元素
            return new TreeNode(nums[leftIndex]);
        }
        int maxIndex = leftIndex;// 最大值所在位置
        int maxVal = nums[maxIndex];// 最大值
        for (int i = leftIndex + 1; i < rightIndex; i++) {
            if (nums[i] > maxVal) {
                maxVal = nums[i];
                maxIndex = i;
            }
        }
        TreeNode root = new TreeNode(maxVal);
        // 根据maxIndex划分左右子树
        root.left = constructMaximumBinaryTree1(nums, leftIndex, maxIndex);
        root.right = constructMaximumBinaryTree1(nums, maxIndex + 1, rightIndex);
        return root;
    }
}
