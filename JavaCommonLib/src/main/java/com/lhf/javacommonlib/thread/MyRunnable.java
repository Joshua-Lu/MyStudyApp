package com.lhf.javacommonlib.thread;

import java.util.Date;

/**
 * 开启线程方式一：见{@link MyThread}
 * 开启线程方式二：创建类实现Runnable接口，创建该类对象，传递给Thread
 * Created by Joshua on 2020/9/13 0:37
 */
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("MyRunnable.run() called");
        String name = Thread.currentThread().getName();// 获取当前线程的名称
        System.out.println("MyRunnable.run: name = [" + name + "], startTime = [" + new Date() + "]");
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("MyRunnable.run: name = [" + name + "], i = [" + i + "]");
        }
        System.out.println("MyRunnable.run: name = [" + name + "], endTime = [" + new Date() + "]");
    }
}
