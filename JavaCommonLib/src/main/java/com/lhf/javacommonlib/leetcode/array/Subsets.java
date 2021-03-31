package com.lhf.javacommonlib.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoshuaLu
 * @date 2021/3/31 17:41
 */
public class Subsets {
    /*
    https://leetcode-cn.com/problems/subsets/
    78. 子集
    给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。

    解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

    示例 1：
    输入：nums = [1,2,3]
    输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
    示例 2：
    输入：nums = [0]
    输出：[[],[0]]

    提示：
        1 <= nums.length <= 10
        -10 <= nums[i] <= 10
        nums 中的所有元素 互不相同*/

    @Test
    public void test() {
        int[] nums;
        List<List<Integer>> exp, res;

        // 示例 1
        nums = new int[]{1, 2, 3};
        res = subsets(nums);
        System.out.println("Subsets.test: res = [" + res + "]");

    }

    public List<List<Integer>> subsets(int[] nums) {
        return subsets1(nums, nums.length);
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> subsets1(int[] nums, int length) {
        if (length == 0) {
            res.add(new ArrayList<>(list));
            return res;
        }
        // 当前位不添加
        subsets1(nums, length - 1);
        // 当前位添加
        list.add(nums[length - 1]);
        subsets1(nums, length - 1);
        // 回溯
        list.remove(list.size() - 1);
        return res;
    }
}
