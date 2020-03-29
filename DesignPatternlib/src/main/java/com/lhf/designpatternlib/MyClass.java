package com.lhf.designpatternlib;

import com.lhf.designpatternlib.adapter.Adaptee;
import com.lhf.designpatternlib.adapter.ClassAdapter;
import com.lhf.designpatternlib.adapter.ObjectAdapter;
import com.lhf.designpatternlib.adapter.Target;
import com.lhf.designpatternlib.builder.AbstractBuilder;
import com.lhf.designpatternlib.builder.Builder1;
import com.lhf.designpatternlib.builder.Builder2;
import com.lhf.designpatternlib.builder.BuilderProduct;
import com.lhf.designpatternlib.builder.Director;
import com.lhf.designpatternlib.factory.AbstractFactory1;
import com.lhf.designpatternlib.factory.AbstractFactory2;
import com.lhf.designpatternlib.factory.IAbstractFactory;
import com.lhf.designpatternlib.factory.IProductA;
import com.lhf.designpatternlib.factory.IProductB;
import com.lhf.designpatternlib.factory.SimpleFactory;
import com.lhf.designpatternlib.proxy.ISubject;
import com.lhf.designpatternlib.proxy.ProxySubject;
import com.lhf.designpatternlib.proxy.ProxySubject1;
import com.lhf.designpatternlib.proxy.RealSubject;
import com.lhf.designpatternlib.proxy.RealSubject1;
import com.lhf.designpatternlib.singleton.Singleton1;
import com.lhf.designpatternlib.singleton.Singleton2;
import com.lhf.designpatternlib.singleton.Singleton3;
import com.lhf.designpatternlib.singleton.Singleton4;

public class MyClass {
    public static void main(String[] args) {
        System.out.println("---------------- MyClass.main ---------------");
//        testSingleton1();
//        testSingleton2();
//        testSingleton3();
//        testSingleton4();
//        testSimpleFactory();
//        testAbstractFactory();
//        testBuilder();
//        testProxy();
//        testClassAdapter();
        testObjectAdapter();
    }

    private static void testObjectAdapter() {
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }

    private static void testClassAdapter() {
        Target target = new ClassAdapter();
        target.request();
    }

    private static void testProxy() {
        ISubject realSubject = new RealSubject();
        // 传入不同的真实对象，实现对不同真实对象的代理
        realSubject = new RealSubject1();
        ProxySubject proxySubject = new ProxySubject(realSubject);
        // 可以套多层代理
        ProxySubject1 proxySubject1 = new ProxySubject1(proxySubject);
        proxySubject1.request();
    }

    private static void testBuilder() {
        AbstractBuilder builder = new Builder1();
        Director director = new Director(builder);
        BuilderProduct product = director.construct();
        product.show();

        // 传入不同的Builder，就可以生成不同的Product
        builder = new Builder2();
        director = new Director(builder);
        product = director.construct();
        product.show();
    }

    private static void testAbstractFactory() {
        IAbstractFactory factory1 = new AbstractFactory1();
        IProductA productA1 = factory1.createProductA();
        productA1.show();
        IProductB productB1 = factory1.createProductB();
        productB1.show();

        IAbstractFactory factory2 = new AbstractFactory2();
        IProductA productA2 = factory2.createProductA();
        productA2.show();
        IProductB productB2 = factory2.createProductB();
        productB2.show();
    }

    private static void testSimpleFactory() {
        SimpleFactory factory = new SimpleFactory();

        IProductA product = factory.createProduct("A1");
        product.show();

        product = factory.createProduct("A2");
        product.show();
    }

    private static void testSingleton4() {
        System.out.println("MyClass.testSingleton4() called");
        Singleton4 s1 = Singleton4.getInstance();
        Singleton4 s2 = Singleton4.getInstance();
        System.out.println("MyClass.testSingleton4: s1 = [" + s1 + "], s2 = [" + s2 + "]");
        System.out.println("MyClass.testSingleton4: (s1 == s2) = [" + (s1 == s2) + "]");
    }

    private static void testSingleton3() {
        System.out.println("MyClass.testSingleton3() called");
        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton3 s1 = Singleton3.getInstance();
                System.out.println("MyClass.run: s1 = [" + s1 + "]");
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton3 s2 = Singleton3.getInstance();
                System.out.println("MyClass.run: s2 = [" + s2 + "]");
            }
        }).start();
    }

    private static void testSingleton2() {
        System.out.println("MyClass.testSingleton2() called");
        Singleton2 s1 = Singleton2.getInstance();
        Singleton2 s2 = Singleton2.getInstance();
        System.out.println("MyClass.testSingleton2: s1 = [" + s1 + "], s2 = [" + s2 + "]");
        System.out.println("MyClass.testSingleton2: (s1 == s2) = [" + (s1 == s2) + "]");
    }

    private static void testSingleton1() {
        System.out.println("MyClass.testSingleton1() called");
        Singleton1 s1 = Singleton1.getInstance();
        Singleton1 s2 = Singleton1.getInstance();
        System.out.println("MyClass.testSingleton1: s1 = [" + s1 + "], s2 = [" + s2 + "]");
        System.out.println("MyClass.testSingleton1: (s1 == s2) = [" + (s1 == s2) + "]");
    }
}
