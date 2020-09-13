package com.lhf.javacommonlib.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Joshua on 2020/9/13 12:30
 */
public class ThreadTest {
    public static void main(String[] args) {
//        testNewThread();
//        testConcurrentThread();
        testThreadPool();
    }

    private static void testThreadPool() {
        // 1.newFixedThreadPool
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new MyRunnable());
        executorService.submit(new MyRunnable());
        executorService.submit(new MyRunnable());
        // TODO:@lhf testThreadPool:
    }

    private static void testConcurrentThread() {
        ConcurrentRunnable runnable = new ConcurrentRunnable();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    /**
     * 创建开启线程、设置获取线程名称
     */
    private static void testNewThread() {
        // 开启线程方式一：继承Thread
        MyThread myThread = new MyThread();
        myThread.start();// 调用start方法，才会开启新线程，run方法会在一个新的栈中执行
//        myThread.run();// 直接调用run方法，该方法还是在main线程里执行

        String mainThreadName = Thread.currentThread().getName();// 获取当前线程的名称
        System.out.println("MyClass.testThread: mainThreadName = [" + mainThreadName + "]");
        String myThreadName = myThread.getName();// 获取某个线程的名称
        System.out.println("MyClass.testThread: myThreadName = [" + myThreadName + "]");
        myThread.setName("Joshua's Thread线程");// 设置线程名称
        System.out.println("MyClass.testThread: myThread.getName() = [" + myThread.getName() + "]");
        MyThread myNamedThread = new MyThread("lhf's Thread");// 直接传线程名称
        System.out.println("MyClass.testThread: myNamedThread.getName() = [" + myNamedThread.getName() + "]");

        // 开启线程方式二：实现Runnable
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable, "lhf").start();
        // 通常使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("MyClass.run() called");
                System.out.println("MyClass.run: Thread.currentThread().getName() = [" + Thread.currentThread().getName() + "]");
            }
        }).start();

        // 使用Lambda表达式
        new Thread(() -> System.out.println("Use Lambda: Thread.currentThread().getName() = [" + Thread.currentThread().getName() + "]")).start();
    }
}
