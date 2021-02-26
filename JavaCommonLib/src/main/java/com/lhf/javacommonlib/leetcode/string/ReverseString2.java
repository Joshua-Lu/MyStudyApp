package com.lhf.javacommonlib.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Joshua on 2021/2/23.
 */
public class ReverseString2 {

    /*
    https://leetcode-cn.com/problems/reverse-string-ii/
    541. 反转字符串 II
    给定一个字符串 s 和一个整数 k，你需要对从字符串开头算起的每隔 2k 个字符的前 k 个字符进行反转。
    如果剩余字符少于 k 个，则将剩余字符全部反转。
    如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。

    示例:
        输入: s = "abcdefg", k = 2
        输出: "bacdfeg"

    提示：
        该字符串只包含小写英文字母。
        给定字符串的长度和 k 在 [1, 10000] 范围内。
    */

    @Test
    public void test() {
        String s;
        int k;
        String expected;
        String result;

        // 示例 1
        s = "abcdefg";
        k = 2;
        expected = "bacdfeg";
        result = reverseStr(s, k);
        Assert.assertEquals(expected, result);

        // 示例 2
        s = "a";
        k = 2;
        expected = "a";
        result = reverseStr(s, k);
        Assert.assertEquals(expected, result);

    }

    public String reverseStr(String s, int k) {
        char[] chars = s.toCharArray();
        int length = s.length();
        int start = 0;
        int end = 0;
        while (start < length) {
            end = start + k - 1;
            if (end >= length - 1) {
                end = length - 1;
                reverseString(chars, start, end);
                return new String(chars);
            }
            reverseString(chars, start, end);
            start += 2 * k;
        }
        return new String(chars);
    }

    /**
     * 反转字符串指定区间[start, end]的字符
     */
    public void reverseString(char[] chars, int start, int end) {
        System.out.println("ReverseString2.reverseString() called with: chars = [" + Arrays.toString(chars) + "], start = [" + start + "], end = [" + end + "]");
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        System.out.println("ReverseString2.reverseString() returned: " + Arrays.toString(chars));
    }
}
