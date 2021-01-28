package com.lhf.javacommonlib.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/1/20.
 */
public class SearchInsert {
    /*
    https://leetcode-cn.com/problems/search-insert-position/
    35. 搜索插入位置
    给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
    你可以假设数组中无重复元素。

    示例 1:
    输入: [1,3,5,6], 5
    输出: 2
    
    示例 2:
    输入: [1,3,5,6], 2
    输出: 1
    
    示例 3:
    输入: [1,3,5,6], 7
    输出: 4

    示例 4:
    输入: [1,3,5,6], 0
    输出: 0
    */
    @Test
    public void test() {
        int[] nums;
        int target;
        int result;

        // 示例 1
        nums = new int[]{1, 3, 5, 6};
        target = 2;
        result = searchInsert(nums, target);
        Assert.assertEquals(1, result);

        // 示例 2
        nums = new int[]{1, 3, 5, 6};
        target = 7;
        result = searchInsert(nums, target);
        Assert.assertEquals(4, result);

        // 示例 3
        nums = new int[]{1, 3, 5, 6};
        target = 5;
        result = searchInsert(nums, target);
        Assert.assertEquals(2, result);

        // 示例 4
        nums = new int[]{1, 3, 5, 6};
        target = 0;
        result = searchInsert(nums, target);
        Assert.assertEquals(0, result);

    }

    public int searchInsert(int[] nums, int target) {
        return searchInsert2(nums, target);
    }

    /**
     * 直接遍历查找
     * <p>
     * 时间：O(n)
     * 空间：O(1)
     */
    public int searchInsert1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }
        return nums.length;
    }

    /**
     * 二分查找
     * 因 nums 有序且不重复，因此可使用二分查找
     * 参考：https://mp.weixin.qq.com/s/fCf5QbPDtE6SSlZ1yh_q8Q
     * <p>
     * 时间：O(n)
     * 空间：O(1)
     */
    public int searchInsert2(int[] nums, int target) {
        int left = 0;
        int right = nums.length;// 定义target在左闭右开的区间里，[left, right）
        while (left < right) {
            int middle = (left + right) / 2;
            if (target > nums[middle]) {
                left = middle + 1;
            } else if (target < nums[middle]) {
                right = middle;
            } else {
                return middle;
            }
        }
        return right;
    }
}
