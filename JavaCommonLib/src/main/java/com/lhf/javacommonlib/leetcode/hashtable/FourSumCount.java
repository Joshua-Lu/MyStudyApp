package com.lhf.javacommonlib.leetcode.hashtable;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Joshua on 2021/2/20.
 */
public class FourSumCount {
    /*
    https://leetcode-cn.com/problems/4sum-ii/
    454. 四数相加 II
    给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。

    为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。

    例如:

    输入:
        A = [ 1, 2]
        B = [-2,-1]
        C = [-1, 2]
        D = [ 0, 2]

    输出:
        2

    解释:
    两个元组如下:
            1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
            2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
    */
    @Test
    public void test() {
        int[] A = new int[]{1, 2};
        int[] B = new int[]{-2, -1};
        int[] C = new int[]{-1, 2};
        int[] D = new int[]{0, 2};
        int expected = 2;
        int result;
        result = fourSumCount(A, B, C, D);
        Assert.assertEquals(expected, result);

    }


    /**
     * 时间：O(n^2)
     * 空间：O(n^2)
     */
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        // A、B的和为key，和值出现的次数为value
        HashMap<Integer, Integer> countAB = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                countAB.put(a + b, countAB.getOrDefault(a + b, 0) + 1);
            }
        }
        int result = 0;
        for (int c : C) {
            for (int d : D) {
                if (countAB.containsKey(-(c + d))) {
                    result += countAB.get(-(c + d));
                }
            }
        }
        return result;
    }
}
