package com.lhf.javacommonlib.leetcode.twopoints;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Joshua on 2021/2/23.
 */
public class ThreeSum {

    /*
    https://leetcode-cn.com/problems/3sum/
    15. 三数之和
    给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。

    注意：答案中不可以包含重复的三元组。

    示例 1：
        输入：nums = [-1,0,1,2,-1,-4]
        输出：[[-1,-1,2],[-1,0,1]]
    示例 2：
        输入：nums = []
        输出：[]
    示例 3：
        输入：nums = [0]
        输出：[]

    提示：
        0 <= nums.length <= 3000
        -105 <= nums[i] <= 105
    */

    @Test
    public void test() {
        int[] nums;
        List<List<Integer>> expected;
        List<List<Integer>> result;

        // 示例 1
        nums = new int[]{-1, 0, 1, 2, -1, -4};
        expected = Arrays.asList(Arrays.asList(-1, -1, 2), Arrays.asList(-1, 0, 1));
        result = threeSum(nums);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 2
        nums = new int[]{};
        expected = Arrays.asList();
        result = threeSum(nums);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

        // 示例 3
        nums = new int[]{0};
        expected = Arrays.asList();
        result = threeSum(nums);
        Assert.assertArrayEquals(expected.toArray(), result.toArray());

    }

    /**
     * 时间：O(n^2)
     * 空间：O(n)
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) {
            return result;
        }
        // 要求不能重复，所以先排序，方便避免重复
        Arrays.sort(nums);
        System.out.println("ThreeSum.threeSum() called with: nums = [" + Arrays.toString(nums) + "]");
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i];
            // 保证和上一个循环的数不同，避免重复
            if (i > 0 && a == nums[i - 1]) {
                continue;
            }
            // k 初始值定义放在，第二层循环(j)外，因为数组有序，j增大，k肯定减小
            // 这也是减少时间复杂度的关键，减少一层循环
            int k = nums.length - 1;
            for (int j = i + 1; j < nums.length; j++) {
                int b = nums[j];
                // 保证和上一个循环的数不同，避免重复
                if (j > i + 1 && b == nums[j - 1]) {
                    continue;
                }
                int c = nums[k];
                while (j < k && a + b + c > 0) {
                    k--;
                    c = nums[k];
                }
                // j == k 时，就可以结束第二层循环(j)了
                if (j == k) {
                    break;
                }
                if (a + b + c == 0) {
                    System.out.println("ThreeSum.threeSum: i = [" + i + "]");
                    System.out.println("ThreeSum.threeSum: j = [" + j + "]");
                    System.out.println("ThreeSum.threeSum: k = [" + k + "]");
                    List<Integer> list = Arrays.asList(a, b, c);
                    result.add(list);
                }
            }
        }
        System.out.println("ThreeSum.threeSum() returned: " + result.toString());
        return result;
    }
}
