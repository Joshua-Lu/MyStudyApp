package com.lhf.javacommonlib.leetcode.hashtable;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

/**
 * Created by Joshua on 2021/2/3.
 */
public class HappyNumber {

    /*
    202. 快乐数
    编写一个算法来判断一个数 n 是不是快乐数。

    「快乐数」定义为：
        对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
        然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
        如果 可以变为  1，那么这个数就是快乐数。
        如果 n 是快乐数就返回 true ；不是，则返回 false 。

    示例 1：
        输入：19
        输出：true
        解释：
            1^2 + 9^2 = 82
            8^2 + 2^2 = 68
            6^2 + 8^2 = 100
            1^2 + 0^2 + 0^2 = 1
    示例 2：
        输入：n = 2
        输出：false


    提示：
        1 <= n <= 231 - 1
    */

    @Test
    public void test() {
        // 示例 1
        Assert.assertTrue(isHappy(19));
        // 示例 2
        Assert.assertFalse(isHappy(2));
    }

    public boolean isHappy(int n) {
//        return isHappy1(n);
        return isHappy2(n);
    }

    /**
     * 利用HashSet实现
     * 因为对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     * 重复这个过程要么无限循环，要么变成1。
     * 所以只要判断有重复，就返回false，否则，出现1，则返回true即可。
     */
    public boolean isHappy1(int n) {
        HashSet<Integer> set = new HashSet<>();
        while (n != 1) {
            if (set.contains(n)) {
                return false;
            } else {
                set.add(n);
                n = getSumOfSquares(n);
            }
        }
        return true;
    }

    /**
     * 利用快慢指针实现，相当于判断链表是否有环，
     * 并判断环入口值是否为1
     */
    public boolean isHappy2(int n) {
        int slow = getSumOfSquares(n);
        int fast = getSumOfSquares(getSumOfSquares(n));
        while (slow != fast) {
            slow = getSumOfSquares(slow);
            fast = getSumOfSquares(getSumOfSquares(fast));
        }
        return slow == 1;
    }

    /**
     * 计算正整数每个位置上的数字的平方和
     */
    private int getSumOfSquares(int n) {
        int sum = 0;
        while (n != 0) {
            sum += (n % 10) * (n % 10);
            n /= 10;
        }
        return sum;
    }

    @Test
    public void testGetSumOfSquares() {
        Assert.assertEquals(82, getSumOfSquares(19));
        Assert.assertEquals(68, getSumOfSquares(82));
        Assert.assertEquals(100, getSumOfSquares(68));
        Assert.assertEquals(1, getSumOfSquares(100));
    }
}
