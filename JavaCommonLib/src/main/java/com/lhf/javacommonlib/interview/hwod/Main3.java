package com.lhf.javacommonlib.interview.hwod;

import java.util.Scanner;

/**
 * 华为OD机试3
 * <p>
 * 输入：453344
 * 输出：53344
 * 解释：4有3个，要删掉一个，分别删掉其中一个，得所有可能为53344、45334、45334，其中最大值为53344
 * <p>
 * 输入纯数字的字符串，删除其中数字个数大于2的，
 * 保证删除后所有数字个数都小于3，并且输出所有可能里数值最大的
 *
 * @author Joshua
 * @date 2022/12/13 15:33
 */
public class Main3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s = sc.nextLine();
            char[] chars = s.toCharArray();
            int[] map = new int[10];
            // 计数
            for (char c : chars) {
                map[c - '0'] += 1;
            }

            // 遍历删除
            for (int i = 0; i < chars.length; i++) {

            }
        }
    }
}
