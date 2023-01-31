package com.lhf.javacommonlib;

import java.util.Arrays;

/**
 * @author Joshua
 * @date 2022/12/4 22:37
 */
public class TestLeetCode {
    public static void main(String[] args) {
        int[] nums = new int[]{3, -1, 0, 2};
        int k = 3;
        int res = largestSumAfterKNegations(nums, k);
        System.out.println("TestLeetCode.main: res = [" + res + "]");
    }

    public static int largestSumAfterKNegations(int[] nums, int k) {
        // 按绝对值大小排序
        nums = Arrays.stream(nums).boxed().sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1)).mapToInt(Integer::intValue).toArray();
        System.out.println("TestLeetCode.largestSumAfterKNegations: nums = [" + Arrays.toString(nums) + "]");
        for (int i = 0; i < nums.length; i++) {
            // 先将所有的负数取反
            if (nums[i] < 0) {
                nums[i] *= -1;
                k--;
                if (k == 0) {
                    break;
                }
            }
        }
        // 如果k还剩奇数，则将绝对值最小的取反
        if (k % 2 != 0) {
            nums[nums.length - 1] *= -1;
        }
        int res = 0;
        for (int a : nums) {
            res += a;
        }
        return res;
    }
}
