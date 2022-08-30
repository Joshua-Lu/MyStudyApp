package com.lhf.javacommonlib.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Joshua
 * @date 2022/8/26 0:09
 */
public class Partition {
    /*
    https://leetcode.cn/problems/palindrome-partitioning/
    131. 分割回文串
    给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。

    回文串 是正着读和反着读都一样的字符串。*/

    List<List<String>> res = new ArrayList<>();
    List<String> path = new ArrayList<>();

    public static void main(String[] args) {
        List<List<String>> res = new Partition().partition("aab");
        System.out.println("Partition.main: res = [" + res + "]");
    }

    public List<List<String>> partition(String s) {
        backtracking(s, 0);
        return res;
    }

    public void backtracking(String s, int start) {
        if (start == s.length()) {
            res.add(new ArrayList(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            // 判断[start, i]区间子串，是否是回文串
            String s1 = s.substring(start, i + 1);
            if (isHuiWen(s1)) {
                path.add(s1);
                backtracking(s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    public boolean isHuiWen(String s) {
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}
