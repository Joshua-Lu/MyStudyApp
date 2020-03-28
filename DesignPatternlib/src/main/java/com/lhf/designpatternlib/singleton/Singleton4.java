package com.lhf.designpatternlib.singleton;

/**
 * 单例模式四种类型：饿汉模式、懒汉模式（线程不安全）、双重锁懒汉模式、静态内部类模式
 * 4.静态内部类模式
 * Created by Joshua on 2020/3/28 14:50.
 */
public class Singleton4 {
    private static class SingletonHolder {
        private static final Singleton4 instance = new Singleton4();
    }

    private Singleton4() {
    }

    public static Singleton4 getInstance() {
        return SingletonHolder.instance;
    }
}
