package com.lhf.javacommonlib.thread

import org.junit.Before
import org.junit.Test
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.TimeUnit

/**
 * @author Joshua
 * @date 2021/12/7 22:16
 */
class TraceThreadPoolExecutorTest {

    private lateinit var executor: TraceThreadPoolExecutor

    @Before
    fun setUp() {
        executor = TraceThreadPoolExecutor(5, 5, 60L, TimeUnit.SECONDS, ArrayBlockingQueue<Runnable>(10))
    }

    @Test
    fun submit() {
        for (i in 0..5) {
            println(i)
            executor.submit { 10 / i }
        }
    }

    @Test
    fun execute() {
        for (i in 0..5) {
            executor.execute { 10 / i }
        }
    }
}