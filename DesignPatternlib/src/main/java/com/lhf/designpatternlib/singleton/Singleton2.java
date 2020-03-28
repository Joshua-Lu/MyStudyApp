package com.lhf.designpatternlib.singleton;

/**
 * 单例模式四种类型：饿汉模式、懒汉模式（线程不安全）、双重锁懒汉模式、静态内部类模式
 * 2.懒汉模式
 * Created by Joshua on 2020/3/28 14:50.
 */
public class Singleton2 {
    // 懒汉模式在getInstance()中才创建对象
    private static Singleton2 instance;

    // 要将默认构造方法设为private，保证只能通过getInstance()获取对象
    private Singleton2() {
    }

    // 对外的获取对象的方法，保证应用中只有一个该类的对象
    public static Singleton2 getInstance() {
        if (instance == null) {
            instance = new Singleton2();
        }
        return instance;
    }
}
