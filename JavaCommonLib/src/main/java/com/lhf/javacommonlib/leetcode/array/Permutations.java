package com.lhf.javacommonlib.leetcode.array;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JoshuaLu
 * @date 2021/4/19 15:53
 */
public class Permutations {
    /*
    https://leetcode-cn.com/problems/permutations/
    46. 全排列
    给定一个 没有重复 数字的序列，返回其所有可能的全排列。

    示例:
    输入: [1,2,3]
    输出:
            [
            [1,2,3],
            [1,3,2],
            [2,1,3],
            [2,3,1],
            [3,1,2],
            [3,2,1]
            ]*/

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 3};
        List<List<Integer>> result = permute(nums);
        System.out.println("Permutations.test: result = [" + result + "]");
    }

    List<List<Integer>> result = new ArrayList<>();
    List<Integer> path = new ArrayList<>();
    boolean[] used;

    public List<List<Integer>> permute(int[] nums) {
        result.clear();
        path.clear();
        used = new boolean[nums.length];
        backtrack(nums, nums.length);
        return result;
    }

    public void backtrack(int[] numList, int length) {
        System.out.println("Permutations.backtrack() called with: numList = [" + Arrays.toString(numList) + "], length = [" + length + "]");
        if (length < 0) {
            return;
        }
        if (length == 0) {
            System.out.println("Permutations.backtrack: path = [" + path + "]");
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < numList.length; i++) {
            if (!used[i]) {
                System.out.println("Permutations.backtrack: numList[" + i + "] = [" + numList[i] + "]");
                path.add(numList[i]);
                used[i] = true;
                backtrack(numList, length - 1);
                path.remove(path.size() - 1);
                used[i] = false;
            }
        }
    }
}
