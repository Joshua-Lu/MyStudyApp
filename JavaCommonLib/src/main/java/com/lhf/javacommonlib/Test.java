package com.lhf.javacommonlib;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Created by Joshua on 2020/11/24 16:04
 */
public class Test {
    static abstract class A {

        int ivar = 7;

        public A() {
            System.out.println("A.A() called");
        }

        void m1() {
            System.out.println("A.m1() called");
        }

        void m2() {
            System.out.println("A.m2() called");
        }

        void m3() {
            System.out.println("A.m3() called");
        }
    }

    static class B extends A {

        public B() {
            System.out.println("B.B() called");
        }

        public B(int a) {
            System.out.println("B.B() called with: a = [" + a + "]");
        }

        @Override
        void m1() {
            System.out.println("B.m1() called");
        }
    }

    static class C extends B {

        public C() {
            System.out.println("C.C() called");
        }

        public C(int a) {
            super(a);
            System.out.println("C.C() called with: a = [" + a + "]");
        }

        @Override
        void m3() {
            System.out.println("C.m3() called " + (ivar + 6));
        }
    }

    public static void main(String[] args) {
//        A a = new A();
//        B b = new B();
//        C c = new C();
//        A a2 = new C();
//
//        a2.m1();
//        a2.m2();
//        a2.m3();

//        new C(1);


//        new Test().dailyTemperatures(new int[]{30, 60, 90});

        new Test().getLucky("hvmhoasabaymnmsd", 1);

    }

    // 单调栈
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        // 先将第一个元素的index push进去
        stack.push(0);
        // 从第二个元素开始遍历
        for (int i = 1; i < n; i++) {
            // 小于等于栈顶，则直接将元素的位置i push进去
            if (temperatures[i] <= temperatures[stack.peek()]) {
                stack.push(i);
            } else {
                // 找到栈顶元素小于当前的 temperatures[i]
                while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                    // 两个index相减，即可得到栈顶元素到下一个更高温度的距离
                    System.out.println("Test.dailyTemperatures: i = [" + i + "], stack.peek() = [" + stack.peek() + "]");
                    res[stack.peek()] = i - stack.peek();
                    stack.pop();
                }
                stack.push(i);
            }
        }
        System.out.println("Test.dailyTemperatures: res = [" + Arrays.toString(res) + "]");
        return res;
    }

    public int getLucky(String s, int k) {
        char[] chs = s.toCharArray();
        int[] nums = new int[chs.length];
        // 替换每个字符
        for (int i = 0; i < chs.length; i++) {
            nums[i] = chs[i] - 'a' + 1;
        }
        // 转成数字
        long num = 0;
        for (int n : nums) {
            num = num * 10 + n;
        }

        return (int) f(num, k);
    }

    // 循环转换为各位数字之和
    public long f(long num, int k) {
        while (k > 0) {
            long res = 0;
            System.out.println("Test.f: num = [" + num + "], k = [" + k + "]");
            while (num != 0) {
                res += num % 10;
                num /= 10;
            }
            num = res;
            k--;
        }
        System.out.println("Test.f: num = [" + num + "]");
        return num;
    }
}
