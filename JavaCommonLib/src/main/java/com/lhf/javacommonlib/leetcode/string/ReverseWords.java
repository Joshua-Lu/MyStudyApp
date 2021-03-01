package com.lhf.javacommonlib.leetcode.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

/**
 * Created by Joshua on 2021/2/26.
 */
public class ReverseWords {

    /*
    https://leetcode-cn.com/problems/reverse-words-in-a-string/
    151. 翻转字符串里的单词
    给定一个字符串，逐个翻转字符串中的每个单词。

    说明：
        无空格字符构成一个 单词 。
        输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
        如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。


    示例 1：
        输入："the sky is blue"
        输出："blue is sky the"
    示例 2：
        输入："  hello world!  "
        输出："world! hello"
        解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
    示例 3：
        输入："a good   example"
        输出："example good a"
        解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
    示例 4：
        输入：s = "  Bob    Loves  Alice   "
        输出："Alice Loves Bob"
    示例 5：
        输入：s = "Alice does not even like bob"
        输出："bob like even not does Alice"

    提示：
        1 <= s.length <= 104
        s 包含英文大小写字母、数字和空格 ' '
        s 中 至少存在一个 单词

    进阶：
        请尝试使用 O(1) 额外空间复杂度的原地解法。
    */

    @Test
    public void test() {
        String s;
        String expected;
        String result;

        // 示例 1
        s = "the sky is blue";
        expected = "blue is sky the";
        result = reverseWords(s);
        Assert.assertEquals(expected, result);

        // 示例 2
        s = "  hello world!  ";
        expected = "world! hello";
        result = reverseWords(s);
        Assert.assertEquals(expected, result);

        // 示例 3
        s = "a good   example";
        expected = "example good a";
        result = reverseWords(s);
        Assert.assertEquals(expected, result);

        // 示例 4
        s = "  Bob    Loves  Alice   ";
        expected = "Alice Loves Bob";
        result = reverseWords(s);
        Assert.assertEquals(expected, result);

        // 示例 5
        s = "Alice does not even like bob";
        expected = "bob like even not does Alice";
        result = reverseWords(s);
        Assert.assertEquals(expected, result);


    }

    public String reverseWords(String s) {
        return reverseWords2(s);
    }

    /**
     * 使用Java内置函数实现
     */
    public String reverseWords1(String s) {
        System.out.println("ReverseWords.reverseWords1() called with: s = [" + s + "]");
        // 去除首尾空格
        s = s.trim();
        // 以一个或以上连续空格划分
        String[] splits = s.split(" +");
        // 反转数组
        Collections.reverse(Arrays.asList(splits));
        // 添加空格，返回结果
        return String.join(" ", splits);
    }

    /**
     * 不使用Java内置方法实现
     * <p>
     * 1.去除首尾以及中间多余空格
     * 2.反转整个字符串
     * 3.反转各个单词
     */
    public String reverseWords2(String s) {
        System.out.println("ReverseWords.reverseWords2() called with: s = [" + s + "]");
        // 1.去除首尾以及中间多余空格
        StringBuilder sb = removeSpace(s);
        // 2.反转整个字符串
        reverseString(sb, 0, sb.length() - 1);
        // 3.反转各个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    private StringBuilder removeSpace(String s) {
        System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        System.out.println("ReverseWords.removeSpace returned: sb = [" + sb + "]");
        return sb;
    }

    /**
     * 反转字符串指定区间[start, end]的字符
     */
    public void reverseString(StringBuilder sb, int start, int end) {
        System.out.println("ReverseWords.reverseString() called with: sb = [" + sb + "], start = [" + start + "], end = [" + end + "]");
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
        System.out.println("ReverseWords.reverseString returned: sb = [" + sb + "]");
    }

    private void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = 1;
        int n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }
}
