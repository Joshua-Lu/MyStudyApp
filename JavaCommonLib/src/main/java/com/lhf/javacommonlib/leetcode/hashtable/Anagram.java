package com.lhf.javacommonlib.leetcode.hashtable;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

/**
 * Created by Joshua on 2021/2/2.
 */
public class Anagram {

    /*
    https://leetcode-cn.com/problems/valid-anagram/
    242. 有效的字母异位词
    给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。

    示例 1:
        输入: s = "anagram", t = "nagaram"
        输出: true
    示例 2:
        输入: s = "rat", t = "car"
        输出: false

    说明:
        你可以假设字符串只包含小写字母。

    进阶:
        如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
    */

    @Test
    public void test() {
        String s;
        String t;

        // 示例 1
        s = "anagram";
        t = "nagaram";
        boolean result = isAnagram(s, t);
        Assert.assertTrue(result);

        // 示例 2
        s = "rat";
        t = "car";
        result = isAnagram(s, t);
        Assert.assertFalse(result);

    }

    public boolean isAnagram(String s, String t) {
        return isAnagram2(s, t);
    }

    /**
     * 用一个数组来计数
     */
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length()) {// 长度不等，直接返回false
            return false;
        }
        int[] record = new int[26];// 符串只包含小写字母
        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            int index = t.charAt(i) - 'a';
            record[index]--;
            // 放这里，可以少遍历一轮record
            if (record[index] < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 进阶:如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
     * 使用HashMap代替int[]
     */
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length()) {// 长度不等，直接返回false
            return false;
        }
        HashMap<String, Integer> record = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            record.compute(String.valueOf(s.charAt(i)), (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
        }
        for (int i = 0; i < t.length(); i++) {
            record.compute(String.valueOf(t.charAt(i)), (key, oldValue) -> oldValue == null ? -1 : oldValue - 1);
            // 放这里，可以少遍历一轮record
            if (record.get(String.valueOf(t.charAt(i))) < 0) {
                return false;
            }
        }
        return true;
    }
}
