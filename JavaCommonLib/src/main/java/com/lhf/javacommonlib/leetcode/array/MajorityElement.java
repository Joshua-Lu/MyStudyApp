package com.lhf.javacommonlib.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author JoshuaLu
 * @date 2021/3/31 17:11
 */
public class MajorityElement {
    /*
    https://leetcode-cn.com/problems/majority-element/
    169. 多数元素
    给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

    你可以假设数组是非空的，并且给定的数组总是存在多数元素。

    示例 1：
        输入：[3,2,3]
        输出：3
    示例 2：
        输入：[2,2,1,1,1,2,2]
        输出：2

    进阶：
    尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。*/

    @Test
    public void test() {
        int[] nums;
        int exp, res;

        // 示例 1
        nums = new int[]{3, 2, 3};
        exp = 3;
        res = majorityElement(nums);
        Assert.assertEquals(exp, res);

        // 示例 2
        nums = new int[]{2, 2, 1, 1, 1, 2, 2};
        exp = 2;
        res = majorityElement(nums);
        Assert.assertEquals(exp, res);

    }

    // 摩尔投票法
    public int majorityElement(int[] nums) {
        int major = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (count == 0) {
                major = nums[i];
            }
            count += major == nums[i] ? 1 : -1;
        }
        return major;
    }
}
