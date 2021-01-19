package com.lhf.javacommonlib.leetcode.sort;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Joshua on 2021/1/8.
 */
public class SortString {
    /*
    https://leetcode-cn.com/problems/increasing-decreasing-string/
    1370. 上升下降字符串

    给你一个字符串 s ，请你根据下面的算法重新构造字符串：

        1.从 s 中选出 最小 的字符，将它 接在 结果字符串的后面。
        2.从 s 剩余字符中选出 最小 的字符，且该字符比上一个添加的字符大，将它 接在 结果字符串后面。
        3.重复步骤 2 ，直到你没法从 s 中选择字符。
        4.从 s 中选出 最大 的字符，将它 接在 结果字符串的后面。
        5.从 s 剩余字符中选出 最大 的字符，且该字符比上一个添加的字符小，将它 接在 结果字符串后面。
        6.重复步骤 5 ，直到你没法从 s 中选择字符。
        7.重复步骤 1 到 6 ，直到 s 中所有字符都已经被选过。

    在任何一步中，如果最小或者最大字符不止一个 ，你可以选择其中任意一个，并将其添加到结果字符串。

    请你返回将 s 中字符重新排序后的 结果字符串 。



    示例 1：
        输入：s = "aaaabbbbcccc"
        输出："abccbaabccba"
        解释：第一轮的步骤 1，2，3 后，结果字符串为 result = "abc"
        第一轮的步骤 4，5，6 后，结果字符串为 result = "abccba"
        第一轮结束，现在 s = "aabbcc" ，我们再次回到步骤 1
        第二轮的步骤 1，2，3 后，结果字符串为 result = "abccbaabc"
        第二轮的步骤 4，5，6 后，结果字符串为 result = "abccbaabccba"
    示例 2：
        输入：s = "rat"
        输出："art"
        解释：单词 "rat" 在上述算法重排序以后变成 "art"
    示例 3：
        输入：s = "leetcode"
        输出："cdelotee"
    示例 4：
        输入：s = "ggggggg"
        输出："ggggggg"
    示例 5：
        输入：s = "spo"
        输出："ops"

    提示：
        1 <= s.length <= 500
        s 只包含小写英文字母。
    */

    @Test
    public void test() {
        String s;
        String result;

        // 示例 1
        s = "aaaabbbbcccc";
        result = sortString(s);
        Assert.assertEquals("abccbaabccba", result);

        // 示例 2
        s = "rat";
        result = sortString(s);
        Assert.assertEquals("art", result);

        // 示例 3
        s = "leetcode";
        result = sortString(s);
        Assert.assertEquals("cdelotee", result);

        // 示例 4
        s = "ggggggg";
        result = sortString(s);
        Assert.assertEquals("ggggggg", result);

        // 示例 5
        s = "spo";
        result = sortString(s);
        Assert.assertEquals("ops", result);

    }

    /**
     * 思路：创建一个大小为 26 的桶，记录每种字符的数量。每次提取最长的上升或下降字符串时，我们直接顺序或逆序遍历这个桶。
     * 在遍历桶的过程中，如果当前桶的计数值不为零，那么将当前桶对应的字符加入到答案中，并将当前桶的计数值减一即可。我们重复这一过程，直到答案字符串的长度与传入的字符串的长度相等。
     * <p>
     * 时间复杂度：O(∣Σ∣×∣s∣)，其中 Σ 为字符集，s 为传入的字符串，在这道题中，字符集为全部小写字母，∣Σ∣=26。
     * 最坏情况下字符串中所有字符都相同，那么对于每一个字符，我们需要遍历一次整个桶。
     * 空间复杂度：O(∣Σ∣)，其中 Σ 为字符集。我们需要和 ∣Σ∣ 等大的桶来记录每一类字符的数量。
     */
    public String sortString(String s) {
        System.out.println("SortString.sortString() called with: s = [" + s + "]");
        StringBuilder result = new StringBuilder();
        int[] charNums = new int[26];// 题目说明只包含小写英文字母，因此大小为26
        for (int i = 0; i < s.length(); i++) {
            charNums[s.charAt(i) - 'a']++;
        }
        // 所有字符用完结束
        while (result.length() < s.length()) {
            // 正序遍历
            for (int i = 0; i < charNums.length; i++) {
                if (charNums[i] > 0) {
                    result.append(((char) (i + 'a')));
                    charNums[i]--;
                }
            }
            // 倒序遍历
            for (int i = charNums.length - 1; i >= 0; i--) {
                if (charNums[i] > 0) {
                    result.append(((char) (i + 'a')));
                    charNums[i]--;
                }
            }
        }
        System.out.println("SortString.sortString() returned: " + result.toString());
        return result.toString();
    }
}
