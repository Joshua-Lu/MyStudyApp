package com.lhf.javacommonlib.leetcode.multithread;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Joshua on 2021/3/26.
 */
public class PrintInOrder {
    /*
    https://leetcode-cn.com/problems/print-in-order/
    1114. 按序打印
    我们提供了一个类：

    public class Foo {
        public void first() { print("first"); }
        public void second() { print("second"); }
        public void third() { print("third"); }
    }
    三个不同的线程 A、B、C 将会共用一个 Foo 实例。

    一个将会调用 first() 方法
    一个将会调用 second() 方法
    还有一个将会调用 third() 方法
    请设计修改程序，以确保 second() 方法在 first() 方法之后被执行，third() 方法在 second() 方法之后被执行。



    示例 1:

    输入: [1,2,3]
    输出: "firstsecondthird"
    解释:
    有三个线程会被异步启动。
    输入 [1,2,3] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 second() 方法，线程 C 将会调用 third() 方法。
    正确的输出是 "firstsecondthird"。
    示例 2:

    输入: [1,3,2]
    输出: "firstsecondthird"
    解释:
    输入 [1,3,2] 表示线程 A 将会调用 first() 方法，线程 B 将会调用 third() 方法，线程 C 将会调用 second() 方法。
    正确的输出是 "firstsecondthird"。


    提示：

    尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
    你看到的输入格式主要是为了确保测试的全面性。*/
    Foo foo = new Foo();
    Runnable first = () -> {
        try {
            foo.first(() -> System.out.println("first"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
    Runnable second = () -> {
        try {
            foo.second(() -> System.out.println("second"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
    Runnable third = () -> {
        try {
            foo.third(() -> System.out.println("third"));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    };
    Runnable[] runnables = new Runnable[]{first, second, third};

    @Test
    public void test() {

        int[] indexs;
        indexs = new int[]{1, 3, 2};
        for (int index : indexs) {
            new Thread(runnables[index - 1]).start();
        }
    }

    class Foo {

        ReentrantLock lock;
        int num;
        Condition condition1, condition2, condition3;

        public Foo() {
            lock = new ReentrantLock();
            condition1 = lock.newCondition();
            condition2 = lock.newCondition();
            condition3 = lock.newCondition();
            num = 1;
        }

        public void first(Runnable printFirst) throws InterruptedException {
            lock.lock();
            try {
                while (num != 1) {
                    condition1.wait();
                }
                // printFirst.run() outputs "first". Do not change or remove this line.
                printFirst.run();
                num = 2;
                condition2.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void second(Runnable printSecond) throws InterruptedException {
            lock.lock();
            try {
                while (num != 2) {
                    condition2.wait();
                }
                // printSecond.run() outputs "second". Do not change or remove this line.
                printSecond.run();
                num = 3;
                condition3.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        public void third(Runnable printThird) throws InterruptedException {
            lock.lock();
            try {
                while (num != 3) {
                    condition3.wait();
                }
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
                num = 1;
                condition1.signal();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
