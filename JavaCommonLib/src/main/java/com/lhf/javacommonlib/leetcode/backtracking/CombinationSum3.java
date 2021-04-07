package com.lhf.javacommonlib.leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoshuaLu
 * @date 2021/4/7 16:44
 */
public class CombinationSum3 {
    /*
https://leetcode-cn.com/problems/combination-sum-iii/
    216. 组合总和 III
    找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。

    说明：

    所有数字都是正整数。
    解集不能包含重复的组合。

    示例 1:
        输入: k = 3, n = 7
        输出: [[1,2,4]]

    示例 2:
        输入: k = 3, n = 9
        输出: [[1,2,6], [1,3,5], [2,3,4]]
    */

    @Test
    public void test() {
        int k, n;

        // 示例 1
        k = 3;
        n = 7;
        combinationSum3(k, n);
        System.out.println("CombinationSum3.test: res = [" + res + "]");

        // 示例 2
        k = 3;
        n = 9;
        combinationSum3(k, n);
        System.out.println("CombinationSum3.test: res = [" + res + "]");

    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        System.out.println("CombinationSum3.combinationSum3() called with: k = [" + k + "], n = [" + n + "]");
        res.clear();
        list.clear();
        backtracking(k, n, 9);
        return res;
    }

    private void backtracking(int k, int n, int maxNum) {
        System.out.println("CombinationSum3.backtracking: list = [" + list + "]");
        System.out.println("CombinationSum3.backtracking() called with: k = [" + k + "], n = [" + n + "], maxNum = [" + maxNum + "]");
        if (k == 0 && n == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 因为不能重复，并且单个数字最大值是maxNum，所以sum最大值为
        // （maxNum + (maxNum - 1) + ... + (maxNum - k + 1)） == k * maxNum - k*(k - 1) / 2
        // 最小数字是 1， 所以sum最小值为
        // (1 + 2 + ... + k) == (1 + k) * k / 2
        if (maxNum == 0
                || n > k * maxNum - k * (k - 1) / 2
                || n < (1 + k) * k / 2) {
            System.out.println("return n = " + n + " sumMax = " + (k * maxNum - k * (k - 1) / 2));
            System.out.println("return n = " + n + " sumMin = " + ((1 + k) * k / 2));
            return;
        }
        list.add(maxNum);
        backtracking(k - 1, n - maxNum, maxNum - 1);
        list.remove(list.size() - 1);
        backtracking(k, n, maxNum - 1);
    }
}
