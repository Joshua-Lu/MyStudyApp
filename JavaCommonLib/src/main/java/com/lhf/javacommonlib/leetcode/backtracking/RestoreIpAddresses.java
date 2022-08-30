package com.lhf.javacommonlib.leetcode.backtracking;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Joshua
 * @date 2022/8/26 22:03
 */
public class RestoreIpAddresses {

    List<String> res = new ArrayList<>();
    List<String> path = new ArrayList<>();

    public static void main(String[] args) {
        new RestoreIpAddresses().restoreIpAddresses("25525511135");
    }

    public List<String> restoreIpAddresses(String s) {
        backtracking(s, 0);
        System.out.println("RestoreIpAddresses.restoreIpAddresses: res = [" + res + "]");
        return res;
    }

    public void backtracking(String s, int start) {
        System.out.println("RestoreIpAddresses.backtracking: path = [" + path + "], start = [" + start + "]");
        if (start == s.length() + 1 && path.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++) {
                sb.append(path.get(i));
                if (i < 3) {
                    sb.append(".");
                }
            }
            res.add(sb.toString());
            return;
        }
        for (int i = start; i < s.length(); i++) {
            // 判断[start, i]区间子串，是否符合要求
            String s1 = s.substring(start, i + 1);
            if (path.size() == 3) {
                s1 = s.substring(start);
                i = s.length();
            }
            if (isValid(s1) && path.size() < 4) {
                path.add(s1);
                backtracking(s, i + 1);
                path.remove(path.size() - 1);
            }
        }
    }

    public boolean isValid(String s) {
        try {
            int a = Integer.valueOf(s);
            if (a >= 0 && a <= 255) {
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }
}
