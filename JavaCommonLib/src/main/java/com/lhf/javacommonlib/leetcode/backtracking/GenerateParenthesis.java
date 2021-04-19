package com.lhf.javacommonlib.leetcode.backtracking;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JoshuaLu
 * @date 2021/4/19 17:13
 */
public class GenerateParenthesis {
    /*
    https://leetcode-cn.com/problems/generate-parentheses/
    22. 括号生成
    数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

    示例 1：
        输入：n = 3
        输出：["((()))","(()())","(())()","()(())","()()()"]
    示例 2：
        输入：n = 1
        输出：["()"]

    提示：
        1 <= n <= 8*/

    @Test
    public void test() {
        generateParenthesis(3);
        System.out.println("GenerateParenthesis.test: result = [" + result + "]");
    }

    List<String> result;
    StringBuilder path;

    public List<String> generateParenthesis(int n) {
        result = new ArrayList<>();
        path = new StringBuilder();
        backtrack(n, n);
        return result;
    }

    /**
     * @param leftRemain  左括号剩几个可用
     * @param rightRemain 右括号剩几个可用
     */
    private void backtrack(int leftRemain, int rightRemain) {
        if (leftRemain == 0 && rightRemain == 0) {
            result.add(path.toString());
            return;
        }
        // leftRemain > rightRemain 不可能出现，出现肯定就无法完成匹配
        if (leftRemain == rightRemain) {
            path.append('(');
            backtrack(leftRemain - 1, rightRemain);
            path.deleteCharAt(path.length() - 1);
        } else if (leftRemain < rightRemain) {
            if (leftRemain > 0) {
                path.append('(');
                backtrack(leftRemain - 1, rightRemain);
                path.deleteCharAt(path.length() - 1);
            }
            path.append(')');
            backtrack(leftRemain, rightRemain - 1);
            path.deleteCharAt(path.length() - 1);
        }
    }
}
