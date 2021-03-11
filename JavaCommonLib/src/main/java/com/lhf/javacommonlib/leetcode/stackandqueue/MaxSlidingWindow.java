package com.lhf.javacommonlib.leetcode.stackandqueue;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/3/11.
 */
public class MaxSlidingWindow {
    /*
    https://leetcode-cn.com/problems/sliding-window-maximum/
    239. 滑动窗口最大值
    给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
    返回滑动窗口中的最大值。

    示例 1：
        输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
        输出：[3,3,5,5,6,7]
        解释：
        滑动窗口的位置                       最大值
        ---------------                    -----
            [1  3  -1] -3  5  3  6  7       3
            1 [3  -1  -3] 5  3  6  7       3
            1  3 [-1  -3  5] 3  6  7       5
            1  3  -1 [-3  5  3] 6  7       5
            1  3  -1  -3 [5  3  6] 7       6
            1  3  -1  -3  5 [3  6  7]      7
    示例 2：
        输入：nums = [1], k = 1
        输出：[1]
    示例 3：
        输入：nums = [1,-1], k = 1
        输出：[1,-1]
    示例 4：
        输入：nums = [9,11], k = 2
        输出：[11]
    示例 5：
        输入：nums = [4,-2], k = 2
        输出：[4]

    提示：
        1 <= nums.length <= 105
        -104 <= nums[i] <= 104
        1 <= k <= nums.length
    */

    @Test
    public void test() {
        int[] nums;
        int k;
        int[] expected;
        int[] result;

        // 示例 1
        nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        k = 3;
        expected = new int[]{3, 3, 5, 5, 6, 7};
        result = maxSlidingWindow(nums, k);
        Assert.assertArrayEquals(expected, result);

        // 示例 2
        nums = new int[]{1};
        k = 1;
        expected = new int[]{1};
        result = maxSlidingWindow(nums, k);
        Assert.assertArrayEquals(expected, result);

        // 示例 3
        nums = new int[]{1, -1};
        k = 1;
        expected = new int[]{1, -1};
        result = maxSlidingWindow(nums, k);
        Assert.assertArrayEquals(expected, result);

        // 示例 4
        nums = new int[]{9, 11};
        k = 2;
        expected = new int[]{11};
        result = maxSlidingWindow(nums, k);
        Assert.assertArrayEquals(expected, result);

        // 示例 5
        nums = new int[]{4, -2};
        k = 2;
        expected = new int[]{4};
        result = maxSlidingWindow(nums, k);
        Assert.assertArrayEquals(expected, result);

        // 示例 6
        nums = new int[]{7, 2, 4};
        k = 2;
        expected = new int[]{7, 4};
        result = maxSlidingWindow(nums, k);
        Assert.assertArrayEquals(expected, result);

        // 示例 7
        nums = new int[]{-7, -8, 7, 5, 7, 1, 6, 0};
        k = 4;
        expected = new int[]{7, 7, 7, 7, 7};
        result = maxSlidingWindow(nums, k);
        Assert.assertArrayEquals(expected, result);

    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 1) {
            return nums;
        }
        MyDescQueue queue = new MyDescQueue();
        int[] result = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            queue.offer(nums[i]);
            if (i < k) {
                if (i == k - 1) {
                    System.out.println("MaxSlidingWindow.maxSlidingWindow: queue = [" + queue + "]");
                    result[i - k + 1] = queue.peek();
                }
            } else {
                queue.poll(nums[i - k]);
                System.out.println("MaxSlidingWindow.maxSlidingWindow: queue = [" + queue + "]");
                result[i - k + 1] = queue.peek();
            }
        }
        return result;
    }
}
