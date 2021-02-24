package com.lhf.javacommonlib.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/2/23.
 */
public class ReverseString {

    /*
    https://leetcode-cn.com/problems/reverse-string/
    344. 反转字符串
    编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
    不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
    你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。

    示例 1：
        输入：["h","e","l","l","o"]
        输出：["o","l","l","e","h"]
    示例 2：
        输入：["H","a","n","n","a","h"]
        输出：["h","a","n","n","a","H"]
    */

    @Test
    public void test() {
        char[] s;
        char[] expected;

        // 示例 1
        s = new char[]{'h', 'e', 'l', 'l', 'o'};
        expected = new char[]{'o', 'l', 'l', 'e', 'h'};
        reverseString(s);
        Assert.assertArrayEquals(expected, s);

        // 示例 2
        s = new char[]{'H', 'a', 'n', 'n', 'a', 'h'};
        expected = new char[]{'h', 'a', 'n', 'n', 'a', 'H'};
        reverseString(s);
        Assert.assertArrayEquals(expected, s);
    }

    public void reverseString(char[] s) {
        int i = 0;
        int j = s.length - 1;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
