package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/25.
 */
public class SortedArrayToBST {
    /*
    https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/
    108. 将有序数组转换为二叉搜索树
    给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。

    高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。

    示例 1：
        输入：nums = [-10,-3,0,5,9]
        输出：[0,-3,9,-10,null,5]
        解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
    示例 2：
        输入：nums = [1,3]
        输出：[3,1]
        解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。

    提示：
        1 <= nums.length <= 104
        -104 <= nums[i] <= 104
        nums 按 严格递增 顺序排列*/

    @Test
    public void test() {

        int[] nums;
        TreeNode exp1, exp2, res;

        // 示例 1
        nums = new int[]{-10, -3, 0, 5, 9};
        exp1 = TreeNode.createTree(new Integer[]{0, -3, 9, -10, null, 5});
        exp2 = TreeNode.createTree(new Integer[]{0, -10, 5, null, -3, null, 9});
        res = sortedArrayToBST(nums);
        System.out.println("SortedArrayToBST.test: res.toArrayString() = [" + res.toArrayString() + "]");
        Assert.assertTrue(res.toArrayString().equals(exp1.toArrayString())
                || res.toArrayString().equalsIgnoreCase(exp2.toArrayString()));

        // 示例 2
        nums = new int[]{1, 3};
        exp1 = TreeNode.createTree(new Integer[]{1, 3});
        exp1 = TreeNode.createTree(new Integer[]{3, 1});
        res = sortedArrayToBST(nums);
        System.out.println("SortedArrayToBST.test: res.toArrayString() = [" + res.toArrayString() + "]");
        Assert.assertTrue(res.toArrayString().equals(exp1.toArrayString())
                || res.toArrayString().equalsIgnoreCase(exp2.toArrayString()));

    }

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }

    public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left >= right) {
            return null;
        }
        if (right - left == 1) {
            return new TreeNode(nums[left]);
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid);
        root.right = sortedArrayToBST(nums, mid + 1, right);
        return root;
    }
}
