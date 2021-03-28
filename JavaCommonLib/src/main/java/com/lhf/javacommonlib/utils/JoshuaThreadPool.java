package com.lhf.javacommonlib.utils;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 *
 * @author Joshua
 * @date 2021/3/28 23:33
 */
public class JoshuaThreadPool {
    private static final String TAG = "JoshuaThreadPool";
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
    private static final int MAX_POOL_SIZE = CPU_COUNT * 2 + 1;
    private static final int KEEP_ALIVE_TIME = 1;
    private static JoshuaThreadPool sThreadPoolInstance = null;
    private WorkThreadFactory mWorkThreadFactory;
    private Executor mExecutor;

    private JoshuaThreadPool(String threadPoolName) {
        this(threadPoolName, CORE_POOL_SIZE, MAX_POOL_SIZE);
    }

    private JoshuaThreadPool(String threadPoolName, int initPoolSize, int maxPoolSize) {
        System.out.println("JoshuaThreadPool.JoshuaThreadPool() called with: threadPoolName = [" + threadPoolName + "], initPoolSize = [" + initPoolSize + "], maxPoolSize = [" + maxPoolSize + "]");
        mWorkThreadFactory = new WorkThreadFactory(threadPoolName);
        BlockingQueue<Runnable> mPoolWorkQueue = new LinkedBlockingQueue<>();
        mExecutor = new ThreadPoolExecutor(
                initPoolSize,
                maxPoolSize,
                KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                mPoolWorkQueue,
                mWorkThreadFactory);
    }

    public static JoshuaThreadPool getInstance() {
        if (sThreadPoolInstance == null) {
            sThreadPoolInstance = new JoshuaThreadPool(TAG);
        }
        return sThreadPoolInstance;
    }

    private boolean isThreadPoolCreated() {
        return sThreadPoolInstance != null;
    }

    private JoshuaThreadPool getThreadPoolInstance() {
        return sThreadPoolInstance;
    }

    private WorkThreadFactory getThreadFactory() {
        return mWorkThreadFactory;
    }

    private void submit(Runnable job) {
        getExecutor().execute(job);
    }

    public void execute(String tidName, Runnable job) {
        JoshuaThreadPool instance = getThreadPoolInstance();
        if (instance != null) {
            WorkThreadFactory threadFactory = instance.getThreadFactory();
            if (threadFactory != null) {
                threadFactory.setThreadName(tidName);
            }

            Executor executor = instance.getExecutor();
            if (executor != null) {
                executor.execute(job);
            }
        }
    }

    public void execute(String threadPoolName, String tidName, Runnable job) {
        JoshuaThreadPool instance = getThreadPoolInstance();
        if (instance != null) {
            WorkThreadFactory threadFactory = instance.getThreadFactory();
            if (threadFactory != null) {
                threadFactory.setThreadPoolName(threadPoolName);
                threadFactory.setThreadName(tidName);
            }

            Executor excutor = instance.getExecutor();
            if (excutor != null) {
                excutor.execute(job);
            }
        }
    }

    private Executor getExecutor() {
        if (mExecutor == null) {
            getInstance();
        }
        return mExecutor;
    }

    private static class WorkThreadFactory implements ThreadFactory {
        private final AtomicInteger mNumber = new AtomicInteger();
        private String mThreadPoolName;
        private String mThreadName;

        private WorkThreadFactory(String threadPoolName) {
            mThreadPoolName = threadPoolName;
        }

        private WorkThreadFactory(String threadPoolName, String threadName) {
            mThreadPoolName = threadPoolName;
            mThreadName = threadName;
        }

        public void setThreadPoolName(String threadPoolName) {
            mThreadPoolName = threadPoolName;
        }

        public void setThreadName(String threadName) {
            mThreadName = threadName;
        }

        @Override
        public Thread newThread(Runnable r) {
            mThreadName = mThreadPoolName + "_" + mThreadName + "-" + mNumber.getAndIncrement();
            return new Thread(r, mThreadName) {
                @Override
                public void run() {
                    super.run();
                }
            };
        }
    }

}
