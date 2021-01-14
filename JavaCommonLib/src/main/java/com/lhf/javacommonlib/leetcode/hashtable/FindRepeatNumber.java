package com.lhf.javacommonlib.leetcode.hashtable;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Joshua on 2021/1/7.
 */
public class FindRepeatNumber {
    /*
    https://leetcode-cn.com/problems/shu-zu-zhong-zhong-fu-de-shu-zi-lcof/
    剑指 Offer 03. 数组中重复的数字

    找出数组中重复的数字。

    在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

    示例 1：
        输入：[2, 3, 1, 0, 2, 5, 3]
        输出：2 或 3

    限制：2 <= n <= 100000
    */

    @Test
    public void test() {
        // 示例 1
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int repeatNumber = findRepeatNumber(nums);
        Assert.assertEquals(2, repeatNumber);
    }

    /**
     * 思路：利用Set集合不能放重复数据的特性
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int findRepeatNumber(int[] nums) {
        System.out.println("FindRepeatNumber.findRepeatNumber() called with: nums = [" + Arrays.toString(nums) + "]");
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            boolean add = set.add(num);
            if (!add) {
                System.out.println("FindRepeatNumber.findRepeatNumber() returned: " + num);
                return num;
            }
        }
        System.out.println("FindRepeatNumber.findRepeatNumber() returned: -1");
        return -1;
    }

    @Test
    public void test1() {
        int[] nums = {2, 3, 1, 0, 2, 5, 3};
        int repeatNumber = findRepeatNumber1(nums);
        Assert.assertEquals(2, repeatNumber);
    }

    /**
     * 思路：由于该题限制，数组 nums 里的所有数字都在 0～n-1 的范围内，
     * 可通过交换，将数字放在对应下标的位置上，
     * 当出现要交换的两个数相同时，即为要找的重复数字
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(1) 在原数组上直接交换，不需要额外空间
     */
    public int findRepeatNumber1(int[] nums) {
        System.out.println("FindRepeatNumber.findRepeatNumber1() called with: nums = [" + Arrays.toString(nums) + "]");
        int temp;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    System.out.println("FindRepeatNumber.findRepeatNumber1() returned: " + nums[i]);
                    return nums[i];
                }
                temp = nums[i];
                nums[i] = nums[temp];
                nums[temp] = temp;
            }
        }
        System.out.println("FindRepeatNumber.findRepeatNumber1() returned: -1");
        return -1;
    }
}
