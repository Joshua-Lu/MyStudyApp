package com.lhf.javacommonlib;

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

        new C(1);
    }
}
