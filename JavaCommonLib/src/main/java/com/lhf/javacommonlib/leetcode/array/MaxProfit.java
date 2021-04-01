package com.lhf.javacommonlib.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author JoshuaLu
 * @date 2021/4/1 9:56
 */
public class MaxProfit {
    /*
    https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/
    122. 买卖股票的最佳时机 II
    给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
    设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
    注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。

    示例 1:
        输入: [7,1,5,3,6,4]
        输出: 7
        解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
        随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
    示例 2:
        输入: [1,2,3,4,5]
        输出: 4
        解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
        注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
        因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
    示例 3:
        输入: [7,6,4,3,1]
        输出: 0
        解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。


    提示：
        1 <= prices.length <= 3 * 10 ^ 4
        0 <= prices[i] <= 10 ^ 4*/

    @Test
    public void test() {
        int[] prices;
        int exp, res;

        // 示例 1
        prices = new int[]{7, 1, 5, 3, 6, 4};
        exp = 7;
        res = maxProfit(prices);
        Assert.assertEquals(exp, res);

        // 示例 2
        prices = new int[]{1, 2, 3, 4, 5};
        exp = 4;
        res = maxProfit(prices);
        Assert.assertEquals(exp, res);

        // 示例 1
        prices = new int[]{7, 6, 4, 3, 1};
        exp = 0;
        res = maxProfit(prices);
        Assert.assertEquals(exp, res);

    }

    public int maxProfit(int[] prices) {
        int res = 0;
        int n = prices.length;
        if (n < 2) {
            return res;
        }
        int inIndex = 0;
        int outIndex = 1;
        while (outIndex < n && inIndex < outIndex) {
            // 涨
            if (prices[outIndex] >= prices[outIndex - 1]) {
                // 最后一个了
                if (outIndex == n - 1) {
                    res += prices[outIndex] - prices[inIndex];
                    return res;
                }
                outIndex++;
            } else {// 跌
                res += prices[outIndex - 1] - prices[inIndex];
                inIndex = outIndex;
                outIndex++;
            }
        }
        return res;
    }
}
