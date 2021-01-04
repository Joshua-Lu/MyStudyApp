package com.lhf.javacommonlib.utils;

import java.util.Scanner;

/**
 * Created by Joshua on 2020/11/24 13:28
 */
public class CommonUtils {
    public static void main(String[] args) {
        String input = CommonUtils.getUserInput("请输入：");
        System.out.println("CommonUtilTest.getUserInput: input = [" + input + "]");
    }

    public static String getUserInput(String prompt) {
        System.out.println(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
