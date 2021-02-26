package com.lhf.javacommonlib.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/2/26.
 */
public class ReplaceSpace {

    /*
    https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof/
    剑指 Offer 05. 替换空格
    请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

    示例 1：
        输入：s = "We are happy."
        输出："We%20are%20happy."

    限制：
        0 <= s 的长度 <= 10000
    */

    @Test
    public void test() {
        // 示例 1
        String s = "We are happy.";
        String expected = "We%20are%20happy.";
        String result = replaceSpace(s);
        Assert.assertEquals(expected, result);

    }

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            sb.append(c == ' ' ? "%20" : c);
        }
        return sb.toString();
    }
}
