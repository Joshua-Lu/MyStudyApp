package com.lhf.javacommonlib.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Joshua on 2021/3/10.
 */
public class VolumeOfHistogram {
    /*
    https://leetcode-cn.com/problems/volume-of-histogram-lcci/
    面试题 17.21. 直方图的水量
    给定一个直方图(也称柱状图)，假设有人从上面源源不断地倒水，最后直方图能存多少水量?直方图的宽度为 1。

    上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的直方图，在这种情况下，可以接 6 个单位的水（蓝色部分表示水）。 感谢 Marcos 贡献此图。

    示例:
        输入: [0,1,0,2,1,0,1,3,2,1,2,1]
        输出: 6
    */

    @Test
    public void test() {
        int[] height;
        int expected;
        int result;

        // 示例 1
        height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        expected = 6;
        result = trap(height);
        Assert.assertEquals(expected, result);
    }

    /**
     * 思路：计算每个位置上的水量，再求和
     * 每个位置上的水量由，它左边的最大值leftMax，右边的最大值rightMax，以及自身位置的值height[i]决定
     * i位置的水量 = Math.max(Math.min(leftMax, rightMax) - height[i], 0)
     */
    public int trap(int[] height) {
        System.out.println("VolumeOfHistogram.trap() called with: height = [" + Arrays.toString(height) + "]");
        int length = height.length;
        if (length < 3) {// 至少要3个，才可能有水
            return 0;
        }
        // 第一个和最后一个位置不用算，肯定是0
        int left = 1;
        int right = length - 2;
        // 初始最大值分别为第一个和最后一个位置的值
        int leftMax = height[0];
        int rightMax = height[length - 1];
        int result = 0;
        while (left <= right) {
            System.out.println("VolumeOfHistogram.trap: left = [" + left + "]");
            System.out.println("VolumeOfHistogram.trap: right = [" + right + "]");
            if (leftMax <= rightMax) {// 计算Max值小那一边的位置
                if (leftMax > height[left]) {
                    result += leftMax - height[left];
                } else {
                    leftMax = height[left];
                }
                left++;
            } else {
                if (rightMax > height[right]) {
                    result += rightMax - height[right];
                } else {
                    rightMax = height[right];
                }
                right--;
            }
        }
        System.out.println("VolumeOfHistogram.trap returned: result = [" + result + "]");
        return result;
    }
}
