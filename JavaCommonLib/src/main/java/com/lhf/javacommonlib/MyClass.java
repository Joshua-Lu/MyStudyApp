package com.lhf.javacommonlib;

import com.lhf.javacommonlib.common.Person;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;

public class MyClass {

    @Test
    public void testHashSet() {
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

    @Test
    public void testHashCode() {
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

    @Test
    public void testCalender() {
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

    @Test
    public void testDateFormat() {
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
