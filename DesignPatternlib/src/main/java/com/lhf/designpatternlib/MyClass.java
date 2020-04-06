package com.lhf.designpatternlib;

import com.lhf.designpatternlib.adapter.Adaptee;
import com.lhf.designpatternlib.adapter.ClassAdapter;
import com.lhf.designpatternlib.adapter.ObjectAdapter;
import com.lhf.designpatternlib.adapter.Target;
import com.lhf.designpatternlib.bridge.Bag;
import com.lhf.designpatternlib.bridge.Green;
import com.lhf.designpatternlib.bridge.HandBag;
import com.lhf.designpatternlib.bridge.IColor;
import com.lhf.designpatternlib.bridge.Red;
import com.lhf.designpatternlib.bridge.Wallet;
import com.lhf.designpatternlib.builder.AbstractBuilder;
import com.lhf.designpatternlib.builder.Builder1;
import com.lhf.designpatternlib.builder.Builder2;
import com.lhf.designpatternlib.builder.BuilderProduct;
import com.lhf.designpatternlib.builder.Director;
import com.lhf.designpatternlib.command.Command1;
import com.lhf.designpatternlib.command.Command2;
import com.lhf.designpatternlib.command.Invoker;
import com.lhf.designpatternlib.composite.Leaf;
import com.lhf.designpatternlib.composite.Root;
import com.lhf.designpatternlib.decorator.ConcreteComponent;
import com.lhf.designpatternlib.decorator.ConcreteDecorator1;
import com.lhf.designpatternlib.decorator.ConcreteDecorator2;
import com.lhf.designpatternlib.decorator.Decorator;
import com.lhf.designpatternlib.decorator.IComponent;
import com.lhf.designpatternlib.facade.Facade;
import com.lhf.designpatternlib.factory.AbstractFactory1;
import com.lhf.designpatternlib.factory.AbstractFactory2;
import com.lhf.designpatternlib.factory.IAbstractFactory;
import com.lhf.designpatternlib.factory.IProductA;
import com.lhf.designpatternlib.factory.IProductB;
import com.lhf.designpatternlib.factory.SimpleFactory;
import com.lhf.designpatternlib.flyweight.FlyweightFactory;
import com.lhf.designpatternlib.flyweight.IFlyweight;
import com.lhf.designpatternlib.flyweight.UnsharedConcreteFlyweight;
import com.lhf.designpatternlib.prototype.Address;
import com.lhf.designpatternlib.prototype.DeepPrototype;
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
import com.lhf.designpatternlib.strategy.Strategy1;
import com.lhf.designpatternlib.strategy.Strategy2;
import com.lhf.designpatternlib.strategy.StrategyContext;
import com.lhf.designpatternlib.template_method.HookConcreteClass;

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
//        testShallowPrototype();
//        testDeepPrototype();
        // 建造者模式
//        testBuilder();
        // 代理模式
//        testProxy();
        // 适配器模式
//        testClassAdapter();
//        testObjectAdapter();
        // 桥接模式
//        testBridge();
        // 装饰模式
//        testDecorator();
        // 外观模式
//        testFacade();
        // 享元模式
//        testFlyweight();
        // 组合模式
//        testComposite();
        // 模板方法模式
//        testTemplateMethod();
        // 策略模式
//        testStrategy();
        // 命令模式
        testCommand();
    }

    private static void testCommand() {
        Invoker invoker = new Invoker(new Command1());
        invoker.call();
        System.out.println("-----------------------");
        invoker.setCommand(new Command2());
        invoker.call();
    }

    private static void testStrategy() {
        StrategyContext context = new StrategyContext(new Strategy1());
        context.strategyMethod();
        System.out.println("----------------");
        context.setStrategy(new Strategy2());
        context.strategyMethod();
    }

    private static void testTemplateMethod() {
        HookConcreteClass hookConcreteClass = new HookConcreteClass();
        hookConcreteClass.templateMethod();
    }

    /**
     * 组合模式
     */
    private static void testComposite() {
        Root root1 = new Root();
        Root root2 = new Root();
        Leaf leaf1 = new Leaf("leaf 1");
        Leaf leaf2 = new Leaf("leaf 2");
        Leaf leaf3 = new Leaf("leaf 3");
        root1.add(leaf1);
        root1.add(root2);
        root2.add(leaf2);
        root2.add(leaf3);
        root1.operation();
    }

    /**
     * 享元模式
     */
    private static void testFlyweight() {
        FlyweightFactory factory = new FlyweightFactory();
        IFlyweight a1 = factory.getFlyweight("a");
        IFlyweight a2 = factory.getFlyweight("a");
        IFlyweight a3 = factory.getFlyweight("a");
        IFlyweight b1 = factory.getFlyweight("b");
        IFlyweight b2 = factory.getFlyweight("b");
        System.out.println();
        a1.operation(new UnsharedConcreteFlyweight("1st a"));
        a2.operation(new UnsharedConcreteFlyweight("2nd a"));
        a3.operation(new UnsharedConcreteFlyweight("3rd a"));
        b1.operation(new UnsharedConcreteFlyweight("1st b"));
        b2.operation(new UnsharedConcreteFlyweight("2nd b"));
    }

    /**
     * 外观模式
     */
    private static void testFacade() {
        Facade facade = new Facade();
        facade.method();
    }

    /**
     * 装饰模式
     */
    private static void testDecorator() {
        IComponent component = new ConcreteComponent();
        Decorator decorator = new ConcreteDecorator1(component);
        decorator.operation();
        System.out.println("-------------------------");
        decorator = new ConcreteDecorator2(component);
        decorator.operation();
    }

    /**
     * 桥接模式
     */
    private static void testBridge() {
        IColor color = new Red();
        Bag bag = new HandBag();
        // 只要改变颜色对象及包对象，就可生成不同颜色及类型的包
        color = new Green();
        bag = new Wallet();

        bag.setColor(color);
        bag.show();
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
     * 深拷贝原型模式
     */
    private static void testDeepPrototype() {
        DeepPrototype deepPrototype1 = new DeepPrototype();
        deepPrototype1.setName("Joshua");
        deepPrototype1.setAge(18);
        deepPrototype1.setAddress(new Address("SH"));
        // 通过序列化方式
//        DeepPrototype deepPrototype2 = (DeepPrototype) deepPrototype1.deepCloneBySerializable();
        // 通过转成json方式
        DeepPrototype deepPrototype2 = (DeepPrototype) deepPrototype1.deepCloneByJson();
        deepPrototype2.setName("lhf");
        deepPrototype2.setAge(20);
        deepPrototype2.getAddress().setCity("ZJ");
        System.out.println("MyClass.testDeepPrototype: (deepPrototype1 == deepPrototype2) = [" + (deepPrototype1 == deepPrototype2) + "]");
        // 深拷贝对于对象成员变量也可以进行拷贝
        System.out.println("MyClass.testDeepPrototype: (deepPrototype1.getAddress() == deepPrototype2.getAddress()) = [" + (deepPrototype1.getAddress() == deepPrototype2.getAddress()) + "]");
        System.out.println("MyClass.testDeepPrototype: deepPrototype1 = [" + deepPrototype1 + "]");
        System.out.println("MyClass.testDeepPrototype: deepPrototype2 = [" + deepPrototype2 + "]");
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
