package com.lhf.javacommonlib.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JoshuaLu
 * @date 2021/4/7 15:04
 */
public class Combinations {
    /*
    https://leetcode-cn.com/problems/combinations/
    77. 组合
    给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。

    示例:

    输入: n = 4, k = 2
    输出:
            [
            [2,4],
            [3,4],
            [2,3],
            [1,2],
            [1,3],
            [1,4],
            ]
    */

    @Test
    public void test() {
        int n, k;
        List<List<Integer>> exp, res;

        // 示例 1
        n = 4;
        k = 2;
        exp = Arrays.asList(Arrays.asList(4, 3), Arrays.asList(4, 2), Arrays.asList(4, 1), Arrays.asList(3, 2), Arrays.asList(3, 1), Arrays.asList(2, 1));
        res = combine(n, k);
        System.out.println("Combinations.test: res = [" + res + "]");
        Assert.assertEquals(exp.toString(), res.toString());
    }

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> list = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        res.clear();
        list.clear();
        backtracking(n, k);
//        backtracking1(n, k);
        return res;
    }

    public void backtracking(int n, int k) {
        System.out.println("Combinations.backtracking() called with: n = [" + n + "], k = [" + k + "], list = [" + list + "]");
        // 剪枝：n < k 时，找到的结果个数肯定少于k，不行
        if (n < k) {
            return;
        }
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = n; i > 0; i--) {
            list.add(i);
            // 关键是这里，下一层递归，n 传 i - 1
            backtracking(i - 1, k - 1);
            list.remove(list.size() - 1);
        }
    }

    /**
     * 这个比较好理解点
     */
    public void backtracking1(int n, int k) {
        System.out.println("Combinations.backtracking() called with: n = [" + n + "], k = [" + k + "], list = [" + list + "]");
        // 剪枝：n < k 时，找到的结果个数肯定少于k，不行
        if (n < k) {
            return;
        }
        if (k == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        // 选择了当前数字，下一层递归 k - 1
        list.add(n);
        backtracking1(n - 1, k - 1);
        // 没有选择当前数字，下一层递归 k 不变
        list.remove(list.size() - 1);
        backtracking1(n - 1, k);
    }
}
