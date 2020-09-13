package com.lhf.javacommonlib.thread;

/**
 * 开启线程方式一：继承Thread，覆写run方法
 * 开启线程方式二：{@link MyRunnable}
 * Created by Joshua on 2020/9/12 21:40
 */
public class MyThread extends Thread {

    public MyThread() {
    }

    public MyThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        super.run();
        System.out.println("MyThread.run() called");
        String name = Thread.currentThread().getName();// 获取当前线程的名称
        for (int i = 0; i < 5; i++) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyThread.run: name = [" + name + "], i = [" + i + "]");
        }
    }
}
