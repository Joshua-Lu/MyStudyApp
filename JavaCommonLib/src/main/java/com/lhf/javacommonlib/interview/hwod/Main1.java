package com.lhf.javacommonlib.interview.hwod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 华为OD机试1
 * <p>
 * 输入：h he hel hell hello
 * 输出：hello
 * <p>
 * 找到字符串数组中的一个最长的字符串，满足从后往前每删除一个元素后，都能在字符串数组中找到该删除后的字符串
 *
 * @author Joshua
 * @date 2022/12/13 14:24
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            String[] words = s.split(" ");
            int max = 0;
            String res = "";
            ArrayList<String> list = new ArrayList<>();
            Collections.addAll(list, words);
            for (String word : words) {
                if (isValid(list, word)) {
                    int len = word.length();
                    if (len > max) {
                        max = len;
                        res = word;
                    }
                }
            }
            System.out.println(res);
        }

    }

    private static boolean isValid(ArrayList<String> list, String word) {
        for (int i = word.length(); i > 0; i--) {
            if (!list.contains(word.substring(0, i))) {
                return false;
            }
        }
        return true;
    }
}
