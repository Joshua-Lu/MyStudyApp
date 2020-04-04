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
import com.lhf.designpatternlib.prototype.Address;
import com.lhf.designpatternlib.prototype.ShallowPrototype;
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
        // 单例模式
//        testSingleton1();
//        testSingleton2();
//        testSingleton3();
//        testSingleton4();
        // 工厂模式
//        testSimpleFactory();
//        testAbstractFactory();
        // 原型模式
        testShallowPrototype();
        // 建造者模式
//        testBuilder();
        // 代理模式
//        testProxy();
        // 适配器模式
//        testClassAdapter();
//        testObjectAdapter();

    }


    /**
     * 对象适配器模式
     */
    private static void testObjectAdapter() {
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }

    /**
     * 类适配器模式
     */
    private static void testClassAdapter() {
        Target target = new ClassAdapter();
        target.request();
    }

    /**
     * 代理模式
     */
    private static void testProxy() {
        ISubject realSubject = new RealSubject();
        // 传入不同的真实对象，实现对不同真实对象的代理
        realSubject = new RealSubject1();
        ProxySubject proxySubject = new ProxySubject(realSubject);
        // 可以套多层代理
        ProxySubject1 proxySubject1 = new ProxySubject1(proxySubject);
        proxySubject1.request();
    }

    /**
     * 建造者模式
     */
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

    /**
     * 浅拷贝原型模式
     */
    private static void testShallowPrototype() {
        ShallowPrototype shallowPrototype1 = new ShallowPrototype();
        shallowPrototype1.setName("Joshua");
        shallowPrototype1.setAge(18);
        shallowPrototype1.setAddress(new Address("SH"));
        try {
            ShallowPrototype shallowPrototype2 = (ShallowPrototype) shallowPrototype1.clone();
            shallowPrototype2.setName("lhf");
            shallowPrototype2.setAge(20);
            shallowPrototype2.getAddress().setCity("ZJ");
            System.out.println("MyClass.testShallowPrototype: (shallowPrototype1 == shallowPrototype2) = [" + (shallowPrototype1 == shallowPrototype2) + "]");
            // 浅拷贝对于对象成员变量无法进行拷贝，还是指向同一个对象地址
            System.out.println("MyClass.testShallowPrototype: (shallowPrototype1.getAddress() == shallowPrototype2.getAddress()) = [" + (shallowPrototype1.getAddress() == shallowPrototype2.getAddress()) + "]");
            System.out.println("MyClass.testShallowPrototype: shallowPrototype1 = [" + shallowPrototype1 + "]");
            System.out.println("MyClass.testShallowPrototype: shallowPrototype2 = [" + shallowPrototype2 + "]");
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 抽象工厂模式
     */
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

    /**
     * 简单工厂模式
     */
    private static void testSimpleFactory() {
        SimpleFactory factory = new SimpleFactory();

        IProductA product = factory.createProduct("A1");
        product.show();

        product = factory.createProduct("A2");
        product.show();
    }

    /**
     * 静态内部类单例模式
     */
    private static void testSingleton4() {
        System.out.println("MyClass.testSingleton4() called");
        Singleton4 s1 = Singleton4.getInstance();
        Singleton4 s2 = Singleton4.getInstance();
        System.out.println("MyClass.testSingleton4: s1 = [" + s1 + "], s2 = [" + s2 + "]");
        System.out.println("MyClass.testSingleton4: (s1 == s2) = [" + (s1 == s2) + "]");
    }

    /**
     * 双重锁懒汉单例模式
     */
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

    /**
     * 懒汉单例模式
     */
    private static void testSingleton2() {
        System.out.println("MyClass.testSingleton2() called");
        Singleton2 s1 = Singleton2.getInstance();
        Singleton2 s2 = Singleton2.getInstance();
        System.out.println("MyClass.testSingleton2: s1 = [" + s1 + "], s2 = [" + s2 + "]");
        System.out.println("MyClass.testSingleton2: (s1 == s2) = [" + (s1 == s2) + "]");
    }

    /**
     * 饿汉单例模式
     */
    private static void testSingleton1() {
        System.out.println("MyClass.testSingleton1() called");
        Singleton1 s1 = Singleton1.getInstance();
        Singleton1 s2 = Singleton1.getInstance();
        System.out.println("MyClass.testSingleton1: s1 = [" + s1 + "], s2 = [" + s2 + "]");
        System.out.println("MyClass.testSingleton1: (s1 == s2) = [" + (s1 == s2) + "]");
    }
}
