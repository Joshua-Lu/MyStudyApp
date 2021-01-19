package com.lhf.javacommonlib.leetcode.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Joshua on 2021/1/18.
 */
public class FrequencySort {
    /*
    https://leetcode-cn.com/problems/sort-array-by-increasing-frequency/
    1636. 按照频率将数组升序排序
    给你一个整数数组 nums ，请你将数组按照每个值的频率 升序 排序。如果有多个值的频率相同，请你按照数值本身将它们 降序 排序。
    请你返回排序后的数组。

    示例 1：
        输入：nums = [1,1,2,2,2,3]
        输出：[3,1,1,2,2,2]
        解释：'3' 频率为 1，'1' 频率为 2，'2' 频率为 3 。
    示例 2：
        输入：nums = [2,3,1,3,2]
        输出：[1,3,3,2,2]
        解释：'2' 和 '3' 频率都为 2 ，所以它们之间按照数值本身降序排序。
    示例 3：
        输入：nums = [-1,1,-6,4,5,-6,1,4,1]
        输出：[5,-1,4,4,-6,-6,1,1,1]

    提示：
        1 <= nums.length <= 100
        -100 <= nums[i] <= 100
    */
    @Test
    public void test() {
        int[] nums;
        int[] expected;
        int[] result;

        // 示例 1
        nums = new int[]{1, 1, 2, 2, 2, 3};
        expected = new int[]{3, 1, 1, 2, 2, 2};
        result = frequencySort1(nums);
        Assert.assertArrayEquals(expected, result);

        // 示例 2
        nums = new int[]{2, 3, 1, 3, 2};
        expected = new int[]{1, 3, 3, 2, 2};
        result = frequencySort1(nums);
        Assert.assertArrayEquals(expected, result);

        // 示例 3
        nums = new int[]{-1, 1, -6, 4, 5, -6, 1, 4, 1};
        expected = new int[]{5, -1, 4, 4, -6, -6, 1, 1, 1};
        result = frequencySort1(nums);
        Assert.assertArrayEquals(expected, result);

    }

    public int[] frequencySort(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int key : nums) {
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + 1);
            } else {
                map.put(key, 1);
            }
        }

        Iterator<Map.Entry<Integer, Integer>> iterator = map.entrySet().iterator();
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> next = iterator.next();
            entryList.add(next);
        }

        entryList.sort((o1, o2) -> {
            if (!o1.getValue().equals(o2.getValue())) {
                return o1.getValue() - o2.getValue();
            } else {
                return o2.getKey() - o1.getKey();
            }
        });

        int index = 0;
        for (Map.Entry<Integer, Integer> entry : entryList) {
            for (int i = 0; i < entry.getValue(); i++) {
                nums[index] = entry.getKey();
                index++;
            }
        }
        return nums;
    }

    /**
     * 与 {@link SortByBits#sortByBits(int[])} 的思路一致
     *
     * @param nums
     * @return
     */
    public int[] frequencySort1(int[] nums) {
        int[] cnts = new int[201];
        for (int n : nums) {
            cnts[n + 100]++;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 201 * cnts[nums[i] + 100] - nums[i] + 100;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            nums[i] = 100 - nums[i] % 201;
        }
        return nums;
    }
}
