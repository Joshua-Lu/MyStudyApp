package com.lhf.javacommonlib.leetcode.dynamicprogram;

import org.junit.Assert;

/**
 * 0-1背包问题
 * <p>
 * 物品重量为 int[] weight
 * 物品价值为 int[] value
 * 背包容量为 int bagSize
 * <p>
 * 求背包能装的最大价值？
 *
 * @author Joshua
 * @date 2022/12/7 23:07
 */
public class BagProblem {
    public static void main(String[] args) {
        int[] weight = new int[]{1, 3, 4};
        int[] value = new int[]{15, 20, 30};
        int bagSize = 4;
        int expRes = 35;

//        int res = bagProblem(weight, value, bagSize);
        int res = bagProblem1(weight, value, bagSize);
        System.out.println("BagProblem.main: res = [" + res + "]");
        Assert.assertEquals(expRes, res);
    }

    /**
     * dp使用二维数组实现
     */
    private static int bagProblem(int[] weight, int[] value, int bagSize) {
        // 物品个数
        int count = weight.length;

        // dp[i][j]表示从前i个物品里取，放到容量为j的背包里，能装的最大价值
        int[][] dp = new int[count + 1][bagSize + 1];

        for (int i = 1; i <= count; i++) {
            for (int j = 0; j <= bagSize; j++) {
                if (j < weight[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }

        return dp[count][bagSize];
    }

    /**
     * dp使用一维数组实现
     */
    private static int bagProblem1(int[] weight, int[] value, int bagSize) {
        // 物品个数
        int count = weight.length;

        // dp[j]表示从前i个物品里取，放到容量为j的背包里，能装的最大价值
        int[] dp = new int[bagSize + 1];

        for (int i = 0; i < count; i++) {
            // j倒序遍历，防止物品价值被多次加入
            for (int j = bagSize; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }

        return dp[bagSize];
    }
}
