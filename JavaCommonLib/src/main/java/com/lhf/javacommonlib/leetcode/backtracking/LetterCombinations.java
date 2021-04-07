package com.lhf.javacommonlib.leetcode.backtracking;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoshuaLu
 * @date 2021/4/7 19:05
 */
public class LetterCombinations {
    /*
    https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/
    17. 电话号码的字母组合
    给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。

    给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

    示例 1：
    输入：digits = "23"
    输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

    示例 2：
    输入：digits = ""
    输出：[]

    示例 3：
    输入：digits = "2"
    输出：["a","b","c"]

    提示：
    0 <= digits.length <= 4
    digits[i] 是范围 ['2', '9'] 的一个数字。*/

    @Test
    public void test() {

        String digits;

        digits = "23";
        letterCombinations(digits);
        System.out.println("LetterCombinations.test: res = [" + res + "]");
        Assert.assertEquals("[ad, ae, af, bd, be, bf, cd, ce, cf]", res.toString());

        digits = "";
        letterCombinations(digits);
        System.out.println("LetterCombinations.test: res = [" + res + "]");
        Assert.assertEquals("[]", res.toString());

        digits = "2";
        letterCombinations(digits);
        System.out.println("LetterCombinations.test: res = [" + res + "]");
        Assert.assertEquals("[a, b, c]", res.toString());
    }

    char[][] map = new char[][]{
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };
    List<String> res;
    StringBuilder path;

    public List<String> letterCombinations(String digits) {
        res = new ArrayList<>();
        path = new StringBuilder();
        if (digits.length() > 0) {
            backtracking(digits, 0);
        }
        return res;
    }

    private void backtracking(String digits, int index) {
        if (index == digits.length() && path.length() > 0) {
            res.add(path.toString());
            return;
        }
        int i = digits.charAt(index) - '2';
        for (char c : map[i]) {
            path.append(c);
            backtracking(digits, index + 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
