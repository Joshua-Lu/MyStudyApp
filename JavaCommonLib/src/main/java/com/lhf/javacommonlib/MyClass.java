package com.lhf.javacommonlib;

import com.lhf.javacommonlib.common.Person;
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
import java.util.HashSet;
import java.util.Locale;

public class MyClass {

    private static void test() {

    }

    public static void main(String[] args) {
        System.out.println("---------------- MyClass.main ---------------");
        test();
        // 测试排序算法
//        testSort();
//        testDateFormat();
//        testCalender();
//        testHashCode();
//        testHashSet();
    }

    private static void testHashSet() {
        // 需重写Person类的hashCode和equals方法，才能实现不存储重复元素
        Person p1 = new Person("Joshua", 18);
        Person p2 = new Person("Joshua", 18);
        Person p3 = new Person("Joshua", 20);
        HashSet<Person> set = new HashSet<>();
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println("MyClass.testHashSet: set = [" + set + "]");
    }

    private static void testHashCode() {
        String s1 = "abc";
        String s2 = "abcd";
        System.out.println("MyClass.testHashCode: s1.hashCode() = [" + s1.hashCode() + "]");
        System.out.println("MyClass.testHashCode: s2.hashCode() = [" + s2.hashCode() + "]");

        // 中文编码格式（UTF-8、GBK）不一样时，hashCode值会不一样
        // GBK编码格式下，"重地"和"通话"的hashCode值一样
        String s3 = "重地";
        String s4 = "通话";
        System.out.println("MyClass.testHashCode: s3.hashCode() = [" + s3.hashCode() + "]");
        System.out.println("MyClass.testHashCode: s4.hashCode() = [" + s4.hashCode() + "]");

        // 不同字符串，hashCode值可能相同
        String s5 = "Aa";
        String s6 = "BB";
        System.out.println("MyClass.testHashCode: s5.hashCode() = [" + s5.hashCode() + "]");
        System.out.println("MyClass.testHashCode: s6.hashCode() = [" + s6.hashCode() + "]");
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
        System.out.println("MyClass.testCalender: calendar = [" + calendar.getTime() + "]");
        int year = calendar.get(Calendar.YEAR);
        System.out.println("MyClass.testCalender: year = [" + year + "]");
        calendar.set(Calendar.YEAR, 2021);
        int yearAfterSet = calendar.get(Calendar.YEAR);
        System.out.println("MyClass.testCalender: yearAfterSet = [" + yearAfterSet + "]");
        calendar.add(Calendar.YEAR, 1);
        int yearAfterAdd = calendar.get(Calendar.YEAR);
        System.out.println("MyClass.testCalender: yearAfterAdd = [" + yearAfterAdd + "]");

        calendar.set(2020, Calendar.MAY, 2);
        System.out.println("MyClass.testCalender: calendar = [" + calendar.getTime() + "]");
        long lovedTime = System.currentTimeMillis() - calendar.getTimeInMillis();
        System.out.println("MyClass.testCalender: lovedTime = [" + lovedTime + "]");
        long lovedDays = lovedTime / 1000 / 60 / 60 / 24;
        System.out.println("MyClass.testCalender: lovedDays = [" + lovedDays + "]");

    }

    private static void testDateFormat() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINESE);
        Date date = new Date();
        // Date 转成 给定格式的时间子串
        String format = sdf.format(date);
        System.out.println("MyClass.testDateFormat: format = [" + format + "]");// 2020-08-30 21:11:37

        Date parse = new Date();
        try {
            // 给定格式的时间子串 转成 Date
            parse = sdf.parse("2020-08-30 21:11:37");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("MyClass.testDateFormat: parse = [" + parse + "]");
    }
}
