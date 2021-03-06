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

    /**
     * 交换数组两个位置的值
     *
     * @param array 要交换的数组
     * @param i     数组位置i
     * @param j     数组位置j
     */
    public static void swap(int[] array, int i, int j) {
        if (i == j) {
            return;
        }
        int tmp = array[j];
        array[j] = array[i];
        array[i] = tmp;
    }

    /**
     * 封装Thread.sleep，这样就不用每个地方都写try,catch
     *
     * @param millis 线程休眠时间 ms
     */
    public static void threadSleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印当前线程名字
     */
    public static void printThreadName() {
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
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
