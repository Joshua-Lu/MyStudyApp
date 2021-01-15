package com.lhf.javacommonlib.leetcode.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Joshua on 2021/1/15.
 */
public class SortByBits {
    /*
    https://leetcode-cn.com/problems/sort-integers-by-the-number-of-1-bits/
    1356. 根据数字二进制下 1 的数目排序
    给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
    如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
    请你返回排序后的数组。

    示例 1：
        输入：arr = [0,1,2,3,4,5,6,7,8]
        输出：[0,1,2,4,8,3,5,6,7]
        解释：[0] 是唯一一个有 0 个 1 的数。
                [1,2,4,8] 都有 1 个 1 。
                [3,5,6] 有 2 个 1 。
                [7] 有 3 个 1 。
        按照 1 的个数排序得到的结果数组为 [0,1,2,4,8,3,5,6,7]
    示例 2：
        输入：arr = [1024,512,256,128,64,32,16,8,4,2,1]
        输出：[1,2,4,8,16,32,64,128,256,512,1024]
        解释：数组中所有整数二进制下都只有 1 个 1 ，所以你需要按照数值大小将它们排序。
    示例 3：
        输入：arr = [10000,10000]
        输出：[10000,10000]
    示例 4：
        输入：arr = [2,3,5,7,11,13,17,19]
        输出：[2,3,5,17,7,11,13,19]
    示例 5：
        输入：arr = [10,100,1000,10000]
        输出：[10,100,10000,1000]

    提示：
        1 <= arr.length <= 500
        0 <= arr[i] <= 10^4
    */
    @Test
    public void test() {

        int[] arr;
        int[] expected;
        int[] result;

        arr = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8};
        expected = new int[]{0, 1, 2, 4, 8, 3, 5, 6, 7};
        result = sortByBits(arr);
        Assert.assertArrayEquals(expected, result);

        arr = new int[]{1024, 512, 256, 128, 64, 32, 16, 8, 4, 2, 1};
        expected = new int[]{1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024};
        result = sortByBits(arr);
        Assert.assertArrayEquals(expected, result);

        arr = new int[]{10000, 10000};
        expected = new int[]{10000, 10000};
        result = sortByBits(arr);
        Assert.assertArrayEquals(expected, result);

        arr = new int[]{2, 3, 5, 7, 11, 13, 17, 19};
        expected = new int[]{2, 3, 5, 17, 7, 11, 13, 19};
        result = sortByBits(arr);
        Assert.assertArrayEquals(expected, result);

        arr = new int[]{10, 100, 1000, 10000};
        expected = new int[]{10, 100, 10000, 1000};
        result = sortByBits(arr);
        Assert.assertArrayEquals(expected, result);

    }

    public int[] sortByBits(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // 因为arr[i] <= 10^4，可以用低5位保存原数据，高位保存1的个数，
            // 这样在1的个数相同的情况下也能直接比较出大小
            arr[i] = getBitCount(arr[i]) * 100000 + arr[i];
        }
        Arrays.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            // 取出低5位，即为原数据
            arr[i] %= 100000;
        }
        return arr;
    }

    private int getBitCount(int n) {
        // 方式一：调系统API方法
        // TODO:@lhf getBitCount: Integer.bitCount算法实现原理
        return Integer.bitCount(n);

        // 方式二：除2法
//        int count = 0;
//        while (n != 0) {
//            count += n % 2;
//            n /= 2;
//        }
//        return count;

        // 方式三：&运算
//        int count = 0;
//        while (n != 0) {
//            n &= n -1;
//            count ++;
//        }
//        return count;
    }
}
