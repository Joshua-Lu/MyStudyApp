package com.lhf.javacommonlib.leetcode.stackandqueue;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Created by Joshua on 2021/3/11.
 */
public class TopKFrequent {
    /*
    https://leetcode-cn.com/problems/top-k-frequent-elements/
    347. 前 K 个高频元素
    给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

    示例 1:
        输入: nums = [1,1,1,2,2,3], k = 2
        输出: [1,2]
    示例 2:
        输入: nums = [1], k = 1
        输出: [1]

    提示：
        你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
        你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
        题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
        你可以按任意顺序返回答案。*/

    @Test
    public void test() {
        int[] nums;
        int k;
        int[] expected;
        int[] result;

        // 示例 1
        nums = new int[]{1, 1, 1, 2, 2, 3};
        k = 2;
        expected = new int[]{1, 2};
        result = topKFrequent(nums, k);
        Assert.assertArrayEquals(expected, result);

        // 示例 2
        nums = new int[]{1};
        k = 1;
        expected = new int[]{1};
        result = topKFrequent(nums, k);
        Assert.assertArrayEquals(expected, result);

        // 示例 2
        nums = new int[]{1, 1, 1, 2, 2, 3, 3, 3, 3, 3};
        k = 2;
        expected = new int[]{3, 1};
        result = topKFrequent(nums, k);
        Assert.assertArrayEquals(expected, result);

    }

    public int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        // 根据map的value值正序排，相当于一个小顶堆
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());
        for (Map.Entry<Integer, Integer> entry : entries) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        for (int i = k - 1; i >= 0; i--) {
            result[i] = queue.poll().getKey();
        }
        return result;
    }
}
