package com.lhf.javacommonlib.interview;

/**
 * 求序列{A,B,C...AA,AB,AC..BA,BB,BC...AAA,AAB...}的第N个
 *
 * @author Joshua
 * @date 2021/5/6 19:47
 */
public class Test1 {
    public static void main(String[] args) {
        int N = 26 * 26 * 26 + 1;

        for (int i = 1; i <= N; i++) {
            result.delete(0, result.length());
            System.out.println("Test1.main: i = [" + i + "]");
            test1(i);
            System.out.println("Test1.main: result = [" + result + "]");
        }
    }

    static StringBuilder result = new StringBuilder();

    private static void test1(int n) {
//        System.out.println("Test1.test1: n = [" + n + "]");
        n -= 1;
        if (n < 0) {
            return;
        }
        Character c = (char) (n % 26 + 'A');
        test1(n / 26);
        result.append(c);
    }
}
