package com.lhf.javacommonlib.thread

import java.util.concurrent.BlockingQueue
import java.util.concurrent.Future
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit


/**
 * 自定义线程池，能打印主线程提交线程任务的堆栈
 *
 * @author Joshua
 * @date 2021/12/7 21:55
 */
class TraceThreadPoolExecutor(corePoolSize: Int, maximumPoolSize: Int, keepAliveTime: Long, unit: TimeUnit?,
                              workQueue: BlockingQueue<Runnable>?)
    : ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue) {

    override fun submit(task: Runnable): Future<*> {
        return super.submit(wrapTask(task, clientTrace()))
    }

    override fun execute(command: Runnable) {
        super.execute(wrapTask(command, clientTrace()))
    }

    private fun wrapTask(task: Runnable, clientTrace: Exception): Runnable {
        return Runnable {
            try {
                task.run()
            } catch (e: Exception) {
                println(e.printStackTrace())
                clientTrace.printStackTrace()
            }
        }
    }

    private fun clientTrace(): Exception {
        return Exception("client stack trace: ${Thread.currentThread().name}")
    }


}