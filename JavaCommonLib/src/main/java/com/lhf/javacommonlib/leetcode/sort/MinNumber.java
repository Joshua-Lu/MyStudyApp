package com.lhf.javacommonlib.leetcode.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Joshua on 2021/1/7.
 */
public class MinNumber {
    /*
    https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof
    剑指 Offer 45. 把数组排成最小的数

    输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

    示例 1:
        输入: [10,2]
        输出: "102"
    示例 2:
        输入: [3,30,34,5,9]
        输出: "3033459"
             
    提示:
        0 < nums.length <= 100
    说明:
        输出结果可能非常大，所以你需要返回一个字符串而不是整数
        拼接起来的数字可能会有前导 0，最后结果不需要去掉前导 0
    */
    @Test
    public void test() {
        int[] nums = {10, 2};
        String result = minNumber(nums);
        Assert.assertEquals("102", result);

        nums = new int[]{3, 30, 34, 5, 9};
        result = minNumber(nums);
        Assert.assertEquals("3033459", result);
    }

    /**
     * 思路：本质是排序，排序后按顺序拼接字串就好了
     * 大小判定规则是：
     * 设 nums 任意两数字的字符串格式 x 和 y ，则
     * 若拼接字符串 x + y > y + x ，则 x > y ；
     * 反之，若 x + y < y + x ，则 x < y ；
     * <p>
     * 作者：jyd
     * 链接：https://leetcode-cn.com/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/solution/mian-shi-ti-45-ba-shu-zu-pai-cheng-zui-xiao-de-s-4/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * <p>
     * 时间复杂度：O(nlogn) 由排序算法决定
     * 空间复杂度：O(n)
     */
    public String minNumber(int[] nums) {
        System.out.println("MinNumber.minNumber() called with: nums = [" + Arrays.toString(nums) + "]");
        StringBuilder result = new StringBuilder();
        String[] numStrings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            numStrings[i] = String.valueOf(nums[i]);
        }
        // 这里改用快排，能提高运行速度
        Arrays.sort(numStrings, (o1, o2) -> (o1 + o2).compareTo((o2 + o1)));
        for (String numString : numStrings) {
            result.append(numString);
        }
        System.out.println("MinNumber.minNumber() returned: " + result.toString());
        return result.toString();
    }
}
