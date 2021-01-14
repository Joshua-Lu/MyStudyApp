package com.lhf.javacommonlib.leetcode.sort;

import com.lhf.javacommonlib.leetcode.sort.unionfind.IUnionFind;
import com.lhf.javacommonlib.leetcode.sort.unionfind.QuickUnionFind;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by Joshua on 2021/1/11.
 */
public class SmallestStringWithSwaps {
    /*
    https://leetcode-cn.com/problems/smallest-string-with-swaps/
    1202. 交换字符串中的元素

    给你一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
    你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
    返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。

    示例 1:
        输入：s = "dcab", pairs = [[0,3],[1,2]]
        输出："bacd"
        解释：
            交换 s[0] 和 s[3], s = "bcad"
            交换 s[1] 和 s[2], s = "bacd"
    示例 2：
        输入：s = "dcab", pairs = [[0,3],[1,2],[0,2]]
        输出："abcd"
        解释：
            交换 s[0] 和 s[3], s = "bcad"
            交换 s[0] 和 s[2], s = "acbd"
            交换 s[1] 和 s[2], s = "abcd"
    示例 3：
        输入：s = "cba", pairs = [[0,1],[1,2]]
        输出："abc"
        解释：
            交换 s[0] 和 s[1], s = "bca"
            交换 s[1] 和 s[2], s = "bac"
            交换 s[0] 和 s[1], s = "abc"

    提示：
        1 <= s.length <= 10^5
        0 <= pairs.length <= 10^5
        0 <= pairs[i][0], pairs[i][1] < s.length
        s 中只含有小写英文字母
    */
    @Test
    public void test() {
        String s;
        List<List<Integer>> pairs;
        String result;

        // 示例 1
        s = "dcab";
        pairs = Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2));
        result = smallestStringWithSwaps(s, pairs);
        Assert.assertEquals("bacd", result);

        // 示例 2
        s = "dcab";
        pairs = Arrays.asList(Arrays.asList(0, 3), Arrays.asList(1, 2), Arrays.asList(0, 2));
        result = smallestStringWithSwaps(s, pairs);
        Assert.assertEquals("abcd", result);

        // 示例 3
        s = "cba";
        pairs = Arrays.asList(Arrays.asList(0, 1), Arrays.asList(1, 2));
        result = smallestStringWithSwaps(s, pairs);
        Assert.assertEquals("abc", result);

    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        System.out.println("SmallestStringWithSwaps.smallestStringWithSwaps() called with: s = [" + s + "], pairs = [" + pairs + "]");
        int length = s.length();
        IUnionFind unionFind = new QuickUnionFind(length);
        for (List<Integer> pair : pairs) {
            unionFind.unionElements(pair.get(0), pair.get(1));
        }

        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            int root = unionFind.find(i);
            if (map.containsKey(root)) {
                map.get(root).offer(s.charAt(i));
            } else {
                PriorityQueue<Character> queue = new PriorityQueue<>();
                queue.offer(s.charAt(i));
                map.put(root, queue);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int root = unionFind.find(i);
            result.append(map.get(root).poll());
        }
        System.out.println("SmallestStringWithSwaps.smallestStringWithSwaps() returned: " + result);
        return result.toString();
    }
}
