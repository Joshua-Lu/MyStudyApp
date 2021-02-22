package com.lhf.javacommonlib.utils;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
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

    @Test
    public void testGetTimeFromNet() {
        long time = getTimeFromNet();
        System.out.println("CommonUtils.updateTimeFromNet: time = [" + time + "]");
    }

    public static long getTimeFromNet() {
        try {
            URL url = new URL("http://baidu.com");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("HEAD");
            long date = urlConnection.getDate();
            System.out.println("CommonUtils.getTimeFromNet: new Date(date) = [" + new Date(date) + "]");
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
