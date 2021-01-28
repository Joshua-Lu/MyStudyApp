package com.lhf.javacommonlib.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Joshua on 2021/1/26.
 */
public class MinSubArrayLen {
    /*
    https://leetcode-cn.com/problems/minimum-size-subarray-sum/
    209. 长度最小的子数组
    给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的 连续 子数组，并返回其长度。
    如果不存在符合条件的子数组，返回 0。

    示例：
        输入：s = 7, nums = [2,3,1,2,4,3]
        输出：2
        解释：子数组 [4,3] 是该条件下的长度最小的子数组。

    进阶：
        如果你已经完成了 O(n) 时间复杂度的解法, 请尝试 O(n log n) 时间复杂度的解法。
    */

    @Test
    public void test() {
        int s;
        int[] nums;
        int result;

        s = 7;
        nums = new int[]{2, 3, 1, 2, 4, 3};
        result = minSubArrayLen(s, nums);
        Assert.assertEquals(2, result);
    }

    public int minSubArrayLen(int s, int[] nums) {
//        return minSubArrayLen1(s, nums);
//        return minSubArrayLen2(s, nums);
        return minSubArrayLen3(s, nums);
    }

    /**
     * 思路：暴力法，两层循环，遍历所有可能
     * <p>
     * 时间：O(n^2)
     * 空间：O(1)
     */
    public int minSubArrayLen1(int s, int[] nums) {
        System.out.println("MinSubArrayLen.minSubArrayLen1() called with: s = [" + s + "], nums = [" + Arrays.toString(nums) + "]");
        // 这里要初始化为最大的整数（或者比nums的长度大的数），
        // 初始化为0的话，后面比较取最小值时，永远是0
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = i; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= s) {
                    result = Math.min(result, j - i + 1);
                    break;
                }
            }
        }
        // result == Integer.MAX_VALUE 说明没有符合条件的，则返回0
        result = result == Integer.MAX_VALUE ? 0 : result;
        System.out.println("MinSubArrayLen.minSubArrayLen1() returned: " + result);
        return result;
    }

    /**
     * 思路：滑动窗口
     * left作为for循环变量
     * <p>
     * 时间：O(n)
     * 空间：O(1)
     */
    public int minSubArrayLen2(int s, int[] nums) {
        System.out.println("MinSubArrayLen.minSubArrayLen2() called with: s = [" + s + "], nums = [" + Arrays.toString(nums) + "]");
        int right = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int left = 0; left < nums.length; left++) {
            System.out.println("MinSubArrayLen.minSubArrayLen2: left = [" + left + "]");
            while (sum < s) {
                if (right < nums.length) {
                    System.out.println("MinSubArrayLen.minSubArrayLen2: right = [" + right + "]");
                    sum += nums[right];
                    System.out.println("MinSubArrayLen.minSubArrayLen2: sum = [" + sum + "]");
                    right++;
                } else {
                    result = result == Integer.MAX_VALUE ? 0 : result;
                    System.out.println("MinSubArrayLen.minSubArrayLen1() returned: " + result);
                    return result;
                }
            }
            result = Math.min(result, right - left);
            System.out.println("MinSubArrayLen.minSubArrayLen2: result = [" + result + "]");
            sum -= nums[left];
        }
        result = result == Integer.MAX_VALUE ? 0 : result;
        System.out.println("MinSubArrayLen.minSubArrayLen1() returned: " + result);
        return result;
    }

    /**
     * 思路：滑动窗口
     * right作为for循环变量，比用left写起来简洁一点
     * 参考：https://mp.weixin.qq.com/s/UrZynlqi4QpyLlLhBPglyg
     * <p>
     * 时间：O(n)
     * 空间：O(1)
     */
    public int minSubArrayLen3(int s, int[] nums) {
        System.out.println("MinSubArrayLen.minSubArrayLen2() called with: s = [" + s + "], nums = [" + Arrays.toString(nums) + "]");
        int left = 0;
        int sum = 0;
        int result = Integer.MAX_VALUE;
        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];
            while (sum >= s) {
                result = Math.min(result, right - left + 1);
                sum -= nums[left++];
            }
        }
        result = result == Integer.MAX_VALUE ? 0 : result;
        System.out.println("MinSubArrayLen.minSubArrayLen1() returned: " + result);
        return result;
    }
}
