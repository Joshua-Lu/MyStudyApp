package com.lhf.javacommonlib.thread;

import com.lhf.javacommonlib.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.LockSupport;

/**
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实时监控元素个数，当个数到5时，线程2给出提示并结束
 * <p>
 * Created by Joshua on 2021/3/28 17:40
 */
public class AddMonitor {

    List<Integer> list = new ArrayList<>();
    static Thread t1, t2;

    public void add(int i) {
        System.out.println("AddMonitor.add() called with: i = [" + i + "]");
        list.add(i);
    }

    public int size() {
        return list.size();
    }

    /**
     * 使用synchronized + lock.notify()、lock.wait()实现
     */
    static class WaitNotifySolution {
        public static void main(String[] args) {
            Object lock = new Object();
            AddMonitor addMonitor = new AddMonitor();
            t1 = new Thread(() -> {
                synchronized (lock) {
                    System.out.println("t1 start");
                    for (int i = 0; i < 10; i++) {
                        addMonitor.add(i);
                        if (addMonitor.size() == 5) {
                            System.out.println("t1 notify");
                            lock.notify();
                            try {
                                System.out.println("t1 wait");
                                lock.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }

                }
            });

            t2 = new Thread(() -> {
                synchronized (lock) {
                    System.out.println("t2 start");
                    try {
                        System.out.println("t2 wait");
                        lock.wait();
                        System.out.println("print when size is 5");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("t2 notify");
                    lock.notify();
                    System.out.println("t2 end");
                }
            });

            t2.start();// t2必须在t1之前启动
            CommonUtils.threadSleep(10);
            t1.start();
        }
    }

    /**
     * 使用两个CountDownLatch实现
     */
    static class CountDownLatchSolution {
        public static void main(String[] args) {
            CountDownLatch latch1 = new CountDownLatch(1);
            CountDownLatch latch2 = new CountDownLatch(1);
            AddMonitor addMonitor = new AddMonitor();
            t1 = new Thread(() -> {
                System.out.println("t1 start");
                for (int i = 0; i < 10; i++) {
                    addMonitor.add(i);
                    if (addMonitor.size() == 5) {
                        System.out.println("t1 countDown");
                        latch1.countDown();
                        try {
                            System.out.println("t1 wait");
                            latch2.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

            });

            t2 = new Thread(() -> {
                System.out.println("t2 start");
                try {
                    System.out.println("t2 wait");
                    latch1.await();
                    System.out.println("print when size is 5");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("t2 countDown");
                latch2.countDown();
                System.out.println("t2 end");
            });

            t2.start();
            t1.start();
        }
    }

    /**
     * 使用两个LockSupport实现
     */
    static class LockSupportSolution {
        public static void main(String[] args) {
            AddMonitor addMonitor = new AddMonitor();
            t1 = new Thread(() -> {
                System.out.println("t1 start");
                for (int i = 0; i < 10; i++) {
                    addMonitor.add(i);
                    if (addMonitor.size() == 5) {
                        System.out.println("t1 unpark");
                        LockSupport.unpark(t2);
                        System.out.println("t1 wait");
                        LockSupport.park();
                    }
                }

            });

            t2 = new Thread(() -> {
                System.out.println("t2 start");
                System.out.println("t2 wait");
                LockSupport.park();
                System.out.println("print when size is 5");
                System.out.println("t2 unpark");
                LockSupport.unpark(t1);
                System.out.println("t2 end");
            });

            t2.start();
            t1.start();
        }
    }
}
