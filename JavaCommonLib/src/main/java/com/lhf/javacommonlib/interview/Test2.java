package com.lhf.javacommonlib.interview;

import com.lhf.javacommonlib.utils.CommonUtils;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程按顺序打印（蚂蚁面试题）
 *
 * @author Joshua
 * @date 2021/5/10 14:54
 */
@SuppressWarnings("AlibabaAvoidManuallyCreateThread")
public class Test2 {
    //评测题目: 编写程序，开启 3 个线程 1,2,3，这三个线程的输出分别为 1、2、3，每个线程将自己的 输出在屏幕上打印 10 遍，
    // 要求输出的结果必须按顺序显示。如：123123....

    volatile int n = 1;
    volatile int count = 10;
    ReentrantLock lock = new ReentrantLock();
    Condition condition = lock.newCondition();

    @Test
    public void testPrint() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count > 0) {
                    lock.lock();
                    try {
                        while (n != 2) {
                            condition.await();
                        }
                        if (count > 0) {
                            System.out.print(2);
                        }
                        n = 3;
                        condition.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count > 0) {
                    lock.lock();
                    try {
                        while (n != 1) {
                            condition.await();
                        }
                        if (count > 0) {
                            System.out.print(1);
                        }
                        n = 2;
                        condition.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count > 0) {
                    lock.lock();
                    try {
                        while (n != 3) {
                            condition.await();
                        }
                        if (count > 0) {
                            System.out.println(3);
                        }
                        n = 1;
                        count--;
                        condition.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }).start();
        CommonUtils.threadSleep(1000);
    }
}
