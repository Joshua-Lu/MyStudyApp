package com.lhf.javacommonlib;

import com.lhf.javacommonlib.sort.BubbleSort;
import com.lhf.javacommonlib.sort.HeapSort;
import com.lhf.javacommonlib.sort.InsertionSort;
import com.lhf.javacommonlib.sort.MergeSort;
import com.lhf.javacommonlib.sort.QuickSort;
import com.lhf.javacommonlib.sort.SelectionSort;
import com.lhf.javacommonlib.sort.ShellSort;
import com.lhf.javacommonlib.sort.SortUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("---------------- MyClass.main ---------------");
        // 测试排序算法
//        testSort();
//        testDateFormat();
        testCalender();
    }

    private static void testSort() {
        int[] inputArray = new int[]{3, 5, 10, 8, 6, 1, 4, 9, 2, 7};
        SortUtil sortUtil = new SortUtil(new BubbleSort());//冒泡排序
        sortUtil.setSort(new SelectionSort());// 选择排序
        sortUtil.setSort(new InsertionSort());// 插入排序
        sortUtil.setSort(new ShellSort());// 希尔排序
        sortUtil.setSort(new MergeSort());// 归并排序
        sortUtil.setSort(new QuickSort());// 快速排序
        sortUtil.setSort(new HeapSort());// 堆排序
        sortUtil.sort(inputArray);
    }

    private static void testCalender() {
        Calendar calendar = Calendar.getInstance();
        System.out.println("Test.main: calendar = [" + calendar.getTime() + "]");
        int year = calendar.get(Calendar.YEAR);
        System.out.println("Test.testCalender: year = [" + year + "]");
        calendar.set(Calendar.YEAR, 2021);
        int yearAfterSet = calendar.get(Calendar.YEAR);
        System.out.println("Test.testCalender: yearAfterSet = [" + yearAfterSet + "]");
        calendar.add(Calendar.YEAR, 1);
        int yearAfterAdd = calendar.get(Calendar.YEAR);
        System.out.println("Test.testCalender: yearAfterAdd = [" + yearAfterAdd + "]");

        calendar.set(2020, Calendar.MAY, 2);
        System.out.println("Test.testCalender: calendar = [" + calendar.getTime() + "]");
        long lovedTime = System.currentTimeMillis() - calendar.getTimeInMillis();
        System.out.println("Test.testCalender: lovedTime = [" + lovedTime + "]");
        long lovedDays = lovedTime / 1000 / 60 / 60 / 24;
        System.out.println("Test.testCalender: lovedDays = [" + lovedDays + "]");

    }

    private static void testDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        Date date = new Date();
        // Date 转成 给定格式的时间子串
        String format = sdf.format(date);
        System.out.println("Test.main: format = [" + format + "]");// 2020-08-30 21:11:37

        Date parse = new Date();
        try {
            // 给定格式的时间子串 转成 Date
            parse = sdf.parse("2020-08-30 21:11:37");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Test.testDateFormat: parse = [" + parse + "]");
    }
}
