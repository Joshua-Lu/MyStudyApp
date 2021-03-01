package com.lhf.javacommonlib.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Joshua on 2021/3/1.
 */
public class StrStr {

    /*
    https://leetcode-cn.com/problems/implement-strstr/
    28. 实现 strStr()
    实现 strStr() 函数。
    给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

    示例 1:
        输入: haystack = "hello", needle = "ll"
        输出: 2
    示例 2:
        输入: haystack = "aaaaa", needle = "bba"
        输出: -1

    说明:
        当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
        对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
    */

    @Test
    public void test() {
        String haystack;
        String needle;
        int expected;
        int result;

        // 示例 1
        haystack = "hello";
        needle = "ll";
        expected = 2;
        result = strStr(haystack, needle);
        Assert.assertEquals(expected, result);

        // 示例 2
        haystack = "aaaaa";
        needle = "bba";
        expected = -1;
        result = strStr(haystack, needle);
        Assert.assertEquals(expected, result);

        // 示例 3
        haystack = "";
        needle = "";
        expected = 0;
        result = strStr(haystack, needle);
        Assert.assertEquals(expected, result);

        // 示例 4
        haystack = "a";
        needle = "a";
        expected = 0;
        result = strStr(haystack, needle);
        Assert.assertEquals(expected, result);

        // 示例 5
        haystack = "mississippi";
        needle = "a";
        expected = -1;
        result = strStr(haystack, needle);
        Assert.assertEquals(expected, result);

    }

    public int strStr(String haystack, String needle) {
//        return strStr1(haystack, needle);
        return strStr2(haystack, needle);
    }


    /**
     * KMP算法
     * 参考：https://mp.weixin.qq.com/s/Gk9FKZ9_FSWLEkdGrkecyg
     * <p>
     * 时间复杂度：O(n)
     * 空间复杂度：O(m)
     * 注：n为haystack的长度，m为needle的长度
     */
    public int strStr2(String haystack, String needle) {
        System.out.println("StrStr.strStr2() called with: haystack = [" + haystack + "], needle = [" + needle + "]");
        int m = needle.length();
        // 当 needle 是空字符串时我们应当返回 0
        if (m == 0) {
            return 0;
        }
        int n = haystack.length();
        if (n < m) {
            return -1;
        }
        int[] next = getNextArray(needle);
        int j = -1;
        for (int i = 0; i < n; i++) {
            System.out.println("StrStr.strStr2: i = [" + i + "]");
            System.out.println("StrStr.strStr2: j = [" + j + "]");
            while (j >= 0 && haystack.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
                System.out.println("StrStr.strStr2: next[j] = [" + j + "]");
            }
            if (haystack.charAt(i) == needle.charAt(j + 1)) {
                j++;
                System.out.println("StrStr.strStr2: j++ = [" + j + "]");
            }
            if (j == m - 1) {// 匹配成功
                System.out.println("StrStr.strStr2 returned: i = [" + i + "]");
                System.out.println("StrStr.strStr2 returned: j = [" + j + "]");
                return i - j;
            }
        }
        return -1;
    }

    @Test
    public void testGetNext() {
        getNextArray("aabaaf");
    }

    private int[] getNextArray(String needle) {
        System.out.println("StrStr.getNextArray() called with: needle = [" + needle + "]");
        int length = needle.length();
        int[] next = new int[length];
        int j = -1;
        next[0] = j;
        // 0位置已经初始化，从1开始遍历
        for (int i = 1; i < length; i++) {
            while (j >= 0 && needle.charAt(i) != needle.charAt(j + 1)) {
                j = next[j];
            }
            if (needle.charAt(i) == needle.charAt(j + 1)) {
                j++;
            }
            next[i] = j;
        }
        System.out.println("StrStr.getNextArray returned: next = [" + Arrays.toString(next) + "]");
        return next;
    }

    /**
     * 基于窗口滑动的算法
     * <p>
     * 时间复杂度：O(m*n)
     * 空间复杂度：O(1)
     * 注：n为haystack的长度，m为needle的长度
     */
    public int strStr1(String haystack, String needle) {
        int m = needle.length();
        // 当 needle 是空字符串时我们应当返回 0
        if (m == 0) {
            return 0;
        }
        int n = haystack.length();
        if (n < m) {
            return -1;
        }
        int i = 0;
        int j = 0;
        while (i < n - m + 1) {
            // 找到首字母相等
            while (i < n && haystack.charAt(i) != needle.charAt(j)) {
                i++;
            }
            if (i == n) {// 没有首字母相等的
                return -1;
            }
            // 遍历后续字符，判断是否相等
            i++;
            j++;
            while (i < n && j < m && haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {// 找到
                return i - j;
            } else {// 未找到
                i -= j - 1;
                j = 0;
            }
        }
        return -1;
    }
}
