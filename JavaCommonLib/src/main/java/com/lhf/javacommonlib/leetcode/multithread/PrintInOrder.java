package com.lhf.javacommonlib.leetcode.multithread;

import com.lhf.javacommonlib.utils.CommonUtils;
import com.lhf.javacommonlib.utils.JoshuaThreadPool;

import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

/**
 * Created by Joshua on 2021/3/26.
 *
 */
public class PrintInOrder {
    /**
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

    static class Foo {

        public Foo() {
            reset();
        }

        public void first(Runnable printFirst) throws InterruptedException {
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
        }

        public void second(Runnable printSecond) throws InterruptedException {
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
        }

        public void third(Runnable printThird) throws InterruptedException {
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
        }

        public void reset() {

        }
    }

    Foo foo;
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

//        foo = new SynchronizedFoo();
//        foo = new CountDownLatchFoo();
        foo = new SemaphoreFoo();

        indexs = new int[]{2, 1, 3};
        foo.reset();
        System.out.println("PrintInOrder.test: indexs = [" + Arrays.toString(indexs) + "]");
        for (int index : indexs) {
            JoshuaThreadPool.getInstance().execute(String.valueOf(index), runnables[index - 1]);
        }
        CommonUtils.threadSleep(100);

        indexs = new int[]{1, 2, 3};
        foo.reset();
        System.out.println("PrintInOrder.test: indexs = [" + Arrays.toString(indexs) + "]");
        for (int index : indexs) {
            JoshuaThreadPool.getInstance().execute(String.valueOf(index), runnables[index - 1]);
        }
        CommonUtils.threadSleep(100);

        indexs = new int[]{3, 2, 1};
        foo.reset();
        System.out.println("PrintInOrder.test: indexs = [" + Arrays.toString(indexs) + "]");
        for (int index : indexs) {
            JoshuaThreadPool.getInstance().execute(String.valueOf(index), runnables[index - 1]);
        }
        CommonUtils.threadSleep(100);
    }

    /**
     * synchronized + int 变量实现
     */
    static class SynchronizedFoo extends Foo {

        int num;

        public SynchronizedFoo() {
            super();
        }

        @Override
        public void reset() {
            num = 1;
        }

        @Override
        public void first(Runnable printFirst) throws InterruptedException {
            synchronized (this) {
                while (num != 1) {
                    wait();
                }
                super.first(printFirst);
                num = 2;
                notifyAll();
            }
        }

        @Override
        public void second(Runnable printSecond) throws InterruptedException {
            synchronized (this) {
                while (num != 2) {
                    wait();
                }
                super.second(printSecond);
                num = 3;
                notifyAll();
            }

        }

        @Override
        public void third(Runnable printThird) throws InterruptedException {
            synchronized (this) {
                while (num != 3) {
                    wait();
                }
                super.third(printThird);
                num = 1;
                notifyAll();
            }
        }
    }

    /**
     * 使用两个CountDownLatch实现
     */
    static class CountDownLatchFoo extends Foo {

        CountDownLatch countDownLatch12;
        CountDownLatch countDownLatch23;

        public CountDownLatchFoo() {
            super();
        }

        @Override
        public void reset() {
            countDownLatch12 = new CountDownLatch(1);
            countDownLatch23 = new CountDownLatch(1);
        }

        @Override
        public void first(Runnable printFirst) throws InterruptedException {
            super.first(printFirst);
            countDownLatch12.countDown();
        }

        @Override
        public void second(Runnable printSecond) throws InterruptedException {
            countDownLatch12.await();
            super.second(printSecond);
            countDownLatch23.countDown();
        }

        @Override
        public void third(Runnable printThird) throws InterruptedException {
            countDownLatch23.await();
            super.third(printThird);
        }
    }

    /**
     * 使用两个Semaphore实现
     * <p>
     * 跟使用CountDownLatch原理基本一样，只是CountDownLatch是为0时才能执行await后面的代码
     * Semaphore是大于0时，才能执行acquire后面的代码
     */
    static class SemaphoreFoo extends Foo {

        Semaphore semaphore12;
        Semaphore semaphore23;

        public SemaphoreFoo() {
            super();
        }

        @Override
        public void reset() {
            semaphore12 = new Semaphore(0);
            semaphore23 = new Semaphore(0);
        }

        @Override
        public void first(Runnable printFirst) throws InterruptedException {
            super.first(printFirst);
            semaphore12.release();
        }

        @Override
        public void second(Runnable printSecond) throws InterruptedException {
            semaphore12.acquire();
            super.second(printSecond);
            semaphore23.release();
        }

        @Override
        public void third(Runnable printThird) throws InterruptedException {
            semaphore23.acquire();
            super.third(printThird);
        }
    }
}
