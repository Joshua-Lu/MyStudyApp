package com.lhf.javacommonlib.thread;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Joshua
 * @date 2021/12/18 15:44
 */
public class LockFreeVectorTest {

    LockFreeVector<Integer> lockFreeVector;
    ExecutorService executor;

    @Before
    public void setUp() throws Exception {
        lockFreeVector = new LockFreeVector<>();
        executor = Executors.newFixedThreadPool(10);
    }

    @Test
    public void test() {
        for (int i = 0; i < 10; i++) {
            System.out.println("LockFreeVectorTest.test: put = [" + i + "]");
            lockFreeVector.pushBack(i);
        }
        for (int i = 0; i < 10; i++) {
            Integer value = lockFreeVector.get(i);
            System.out.println("LockFreeVectorTest.test: i = [" + i + "], value = [" + value + "]");
        }
    }

    @Test
    public void testMultiThread() {
        for (int i = 0; i < 10; i++) {
            System.out.println("LockFreeVectorTest.testMultiThread: put = [" + i + "]");
            int finalI = i;
            executor.submit(() -> lockFreeVector.pushBack(finalI));
        }
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            executor.submit(() -> {
                Integer value = lockFreeVector.get(finalI);
                System.out.println("LockFreeVectorTest.testMultiThread: i = [" + finalI + "], value = [" + value + "]");
            });
        }
    }
}