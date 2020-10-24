package com.lhf.javacommonlib.thread;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Joshua on 2020/9/13 12:30
 */
public class ThreadTest {

    /**
     * 自定义线程池
     */
    @Test
    public void testCustomThreadPool() {
        System.out.println("ThreadTest.testCustomThreadPool() called");
        int corePoolSize = 2;
        int maximumPoolSize = 15;
        long keepAliveTime = 500;
        TimeUnit unit = TimeUnit.MILLISECONDS;
        BlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<Runnable>(10);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
                threadFactory, handler);
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.submit(new MyRunnable());
        }
    }

    /**
     * 系统预定义的ThreadPool
     */
    @Test
    public void testSystemThreadPool() {
        System.out.println("ThreadTest.testSystemThreadPool() called");
        // 1.newFixedThreadPool
        /*return new ThreadPoolExecutor(nThreads, nThreads,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());*/
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(2);
//        fixedThreadPool.submit(new MyRunnable());
//        fixedThreadPool.submit(new MyRunnable());
//        fixedThreadPool.submit(new MyRunnable());

        // 2.newCachedThreadPool
        /*return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());*/
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        cachedThreadPool.submit(new MyRunnable());
//        cachedThreadPool.submit(new MyRunnable());
//        cachedThreadPool.submit(new MyRunnable());

        // 3.newSingleThreadExecutor 不能强转成ThreadPoolExecutor
        /*return new Executors.FinalizableDelegatedExecutorService
                (new ThreadPoolExecutor(1, 1,
                        0L, TimeUnit.MILLISECONDS,
                        new LinkedBlockingQueue<Runnable>()));*/
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        singleThreadExecutor.submit(new MyRunnable());
//        singleThreadExecutor.submit(new MyRunnable());
//        singleThreadExecutor.submit(new MyRunnable());

        // 4.newScheduledThreadPool
        /*public ScheduledThreadPoolExecutor(int corePoolSize) {
            super(corePoolSize, Integer.MAX_VALUE, 0, NANOSECONDS,
                    new ScheduledThreadPoolExecutor.DelayedWorkQueue());
        }*/
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(2);
        // 延时3秒执行
//        scheduledThreadPool.schedule(new MyRunnable(), 3, TimeUnit.SECONDS);
        // 延时1秒执行，之后每隔3秒执行（上一次”结束“到下一次开始）
//        scheduledThreadPool.scheduleWithFixedDelay(new MyRunnable(), 1, 3, TimeUnit.SECONDS);
        // 延时1秒执行，之后每隔3秒执行（上一次”开始“到下一次开始）
        scheduledThreadPool.scheduleAtFixedRate(new MyRunnable(), 1, 3, TimeUnit.SECONDS);
    }

    /**
     * ConcurrentRunnable 实现同步功能的Runnable
     */
    @Test
    public void testConcurrentThread() {
        System.out.println("ThreadTest.testConcurrentThread() called");
        ConcurrentRunnable runnable = new ConcurrentRunnable();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    /**
     * 创建、开启线程
     * 设置、获取线程名称
     */
    @Test
    public void testNewThread() {
        System.out.println("ThreadTest.testNewThread() called");
        // 开启线程方式一：继承Thread
        MyThread myThread = new MyThread();
        myThread.start();// 调用start方法，才会开启新线程，run方法会在一个新的栈中执行
//        myThread.run();// 直接调用run方法，该方法还是在main线程里执行

        String mainThreadName = Thread.currentThread().getName();// 获取当前线程的名称
        System.out.println("ThreadTest.testThread: mainThreadName = [" + mainThreadName + "]");
        String myThreadName = myThread.getName();// 获取某个线程的名称
        System.out.println("ThreadTest.testThread: myThreadName = [" + myThreadName + "]");
        myThread.setName("Joshua's Thread线程");// 设置线程名称
        System.out.println("ThreadTest.testThread: myThread.getName() = [" + myThread.getName() + "]");
        MyThread myNamedThread = new MyThread("lhf's Thread");// 直接传线程名称
        System.out.println("ThreadTest.testThread: myNamedThread.getName() = [" + myNamedThread.getName() + "]");

        // 开启线程方式二：实现Runnable
        MyRunnable myRunnable = new MyRunnable();
        new Thread(myRunnable, "lhf").start();
        // 通常使用匿名内部类
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadTest.run() called");
                System.out.println("ThreadTest.run: Thread.currentThread().getName() = [" + Thread.currentThread().getName() + "]");
            }
        }).start();

        // 使用Lambda表达式
        new Thread(() -> System.out.println("Use Lambda: Thread.currentThread().getName() = [" + Thread.currentThread().getName() + "]")).start();
    }
}
