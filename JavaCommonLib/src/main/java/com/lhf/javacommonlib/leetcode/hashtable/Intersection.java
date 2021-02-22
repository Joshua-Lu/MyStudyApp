package com.lhf.javacommonlib.leetcode.hashtable;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by Joshua on 2021/2/2.
 */
public class Intersection {
    /*
    https://leetcode-cn.com/problems/intersection-of-two-arrays/
    349. 两个数组的交集
    给定两个数组，编写一个函数来计算它们的交集。

    示例 1：
        输入：nums1 = [1,2,2,1], nums2 = [2,2]
        输出：[2]
    示例 2：
        输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
        输出：[9,4]

    说明：
        输出结果中的每个元素一定是唯一的。
        我们可以不考虑输出结果的顺序。
    */

    @Test
    public void test() {
        int[] nums1;
        int[] nums2;
        int[] expected;
        int[] result;

        // 示例 1
        nums1 = new int[]{1, 2, 2, 1};
        nums2 = new int[]{2, 2};
        expected = new int[]{2};
        result = intersection(nums1, nums2);
        Assert.assertArrayEquals(expected, result);

        // 示例 2
        nums1 = new int[]{4, 9, 5};
        nums2 = new int[]{9, 4, 9, 8, 4};
        expected = new int[]{4, 9};
        result = intersection(nums1, nums2);
        Assert.assertArrayEquals(expected, result);
    }

    /**
     * 利用HashSet实现
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        for (int i : nums1) {
            set1.add(i);
        }
        HashSet<Integer> resultSet = new HashSet<>();
        for (int i : nums2) {
            if (set1.contains(i)) {
                resultSet.add(i);
            }
        }
        int[] resultArray = new int[resultSet.size()];
        int index = 0;
        for (Integer integer : resultSet) {
            resultArray[index++] = integer;
        }
        return resultArray;
    }
}
