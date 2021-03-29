package com.lhf.javacommonlib.leetcode.multithread;

import com.lhf.javacommonlib.utils.CommonUtils;
import com.lhf.javacommonlib.utils.JoshuaThreadPool;

import org.junit.Test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.function.IntConsumer;

/**
 * @author JoshuaLu
 * @date 2021/3/29 11:17
 */
public class FizzBuzzTest {
    /**
     * https://leetcode-cn.com/problems/fizz-buzz-multithreaded/
     * 1195. 交替打印字符串
     * 编写一个可以从 1 到 n 输出代表这个数字的字符串的程序，但是：
     * <p>
     * 如果这个数字可以被 3 整除，输出 "fizz"。
     * 如果这个数字可以被 5 整除，输出 "buzz"。
     * 如果这个数字可以同时被 3 和 5 整除，输出 "fizzbuzz"。
     * 例如，当 n = 15，输出： 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz。
     * <p>
     * 假设有这么一个类：
     * <p>
     * class FizzBuzz {
     * public FizzBuzz(int n) { ... }               // constructor
     * public void fizz(printFizz) { ... }          // only output "fizz"
     * public void buzz(printBuzz) { ... }          // only output "buzz"
     * public void fizzbuzz(printFizzBuzz) { ... }  // only output "fizzbuzz"
     * public void number(printNumber) { ... }      // only output the numbers
     * }
     * 请你实现一个有四个线程的多线程版  FizzBuzz， 同一个 FizzBuzz 实例会被如下四个线程使用：
     * <p>
     * 线程A将调用 fizz() 来判断是否能被 3 整除，如果可以，则输出 fizz。
     * 线程B将调用 buzz() 来判断是否能被 5 整除，如果可以，则输出 buzz。
     * 线程C将调用 fizzbuzz() 来判断是否同时能被 3 和 5 整除，如果可以，则输出 fizzbuzz。
     * 线程D将调用 number() 来实现输出既不能被 3 整除也不能被 5 整除的数字。
     * <p>
     * 提示：
     * 本题已经提供了打印字符串的相关方法，如 printFizz() 等，具体方法名请参考答题模板中的注释部分。
     */

    class FizzBuzz {
        private int n;
        private CyclicBarrier cyclicBarrier = new CyclicBarrier(4);

        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 != 0) {
                    printFizz.run();
                }
                try {
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 != 0 && i % 5 == 0) {
                    printBuzz.run();
                }
                try {
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 == 0 && i % 5 == 0) {
                    printFizzBuzz.run();
                }
                try {
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (i % 3 != 0 && i % 5 != 0) {
                    printNumber.accept(i);
                }
                try {
                    cyclicBarrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void test() {
        FizzBuzz fizzBuzz = new FizzBuzz(15);
        System.out.println("expected: 1, 2, fizz, 4, buzz, fizz, 7, 8, fizz, buzz, 11, fizz, 13, 14, fizzbuzz");
        JoshuaThreadPool.getInstance().execute("A", () -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        JoshuaThreadPool.getInstance().execute("B", () -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        JoshuaThreadPool.getInstance().execute("C", () -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        JoshuaThreadPool.getInstance().execute("D", () -> {
            try {
                fizzBuzz.number(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        CommonUtils.threadSleep(2000);
    }
}
