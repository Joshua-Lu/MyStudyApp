package com.lhf.javacommonlib.leetcode.stackandqueue;

import org.junit.Assert;
import org.junit.Test;

import java.util.Stack;

/**
 * Created by Joshua on 2021/3/10 0:59
 */
public class BracketsMatcher {

    /*
    https://leetcode-cn.com/problems/valid-parentheses/
    20. 有效的括号
    给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。

    有效字符串需满足：
        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。


    示例 1：
        输入：s = "()"
        输出：true
    示例 2：
        输入：s = "()[]{}"
        输出：true
    示例 3：
        输入：s = "(]"
        输出：false
    示例 4：
        输入：s = "([)]"
        输出：false
    示例 5：
        输入：s = "{[]}"
        输出：true


    提示：
        1 <= s.length <= 104
        s 仅由括号 '()[]{}' 组成
    */

    @Test
    public void test() {
        String s;
        boolean result;

        // 示例 1：
        s = "()";
        result = isValid(s);
        Assert.assertTrue(result);

        // 示例 2：
        s = "()[]{}";
        result = isValid(s);
        Assert.assertTrue(result);

        // 示例 3：
        s = "(]";
        result = isValid(s);
        Assert.assertFalse(result);

        // 示例 4：
        s = "([)]";
        result = isValid(s);
        Assert.assertFalse(result);

        // 示例 5：
        s = "{[]}";
        result = isValid(s);
        Assert.assertTrue(result);

        // 示例 6：
        s = "]";
        result = isValid(s);
        Assert.assertFalse(result);

        // 示例 7：
        s = "(])";
        result = isValid(s);
        Assert.assertFalse(result);

    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case ')':
                    // 括号匹配，就将对应左括号出栈，否则匹配失败，直接return false
                    if (!stack.empty() && stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case ']':
                    if (!stack.empty() && stack.peek() == '[') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                case '}':
                    if (!stack.empty() && stack.peek() == '{') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
                default:
                    // 左括号直接入栈
                    stack.push(c);
                    break;
            }
        }
        // 遍历完了，stack为空，则匹配成功
        return stack.empty();
    }
}
