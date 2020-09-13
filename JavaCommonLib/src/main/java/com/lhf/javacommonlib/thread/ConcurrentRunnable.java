package com.lhf.javacommonlib.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 处理线程安全问题，3个方式：
 * 1.同步代码块
 * 2.同步方法
 * 3.同步锁
 * <p>
 * Created by Joshua on 2020/9/13 12:40
 */
class ConcurrentRunnable implements Runnable {
    private int count = 100;

    // 锁对象，该对象需保证，对于要同步的几个线程来说，是同一个对象，否则无效。
    private final Object object = new Object();

    // 锁对象，该对象需保证，对于要同步的几个线程来说，是同一个对象，否则无效。
    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        // 1.同步代码块
//        synchronized (object) {
//            normalRun();
//        }

        // 2.同步方法
//        concurrentRun();

        // 3.同步锁
        lock.lock();
        try {
            normalRun();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // unlock()方法最好放在finally块里，保证执行
            lock.unlock();
        }
    }

    // 只需在方法上添加synchronized关键字
    // 该方式相当于 synchronized (this){} 使用this作为锁对象
    private synchronized void concurrentRun() {
        normalRun();
    }

    // 不加同步机制时，访问共享变量count，会存在线程安全问题
    // 会出现count值重复，或小于等于0的情况
    private void normalRun() {
        while (count > 0) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ": count = [" + count + "]");
            count--;
        }
    }
}
