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
import com.lhf.designpatternlib.factory.ProductA1;
import com.lhf.designpatternlib.factory.ProductA2;
import com.lhf.designpatternlib.factory.ProductB1;
import com.lhf.designpatternlib.factory.ProductB2;
import com.lhf.designpatternlib.factory.SimpleFactory;
import com.lhf.designpatternlib.flyweight.FlyweightFactory;
import com.lhf.designpatternlib.flyweight.IFlyweight;
import com.lhf.designpatternlib.flyweight.UnsharedStatus;
import com.lhf.designpatternlib.interpret.Calculator;
import com.lhf.designpatternlib.iterator.Aggregate;
import com.lhf.designpatternlib.iterator.IIterator;
import com.lhf.designpatternlib.mediator.Colleague;
import com.lhf.designpatternlib.mediator.Colleague1;
import com.lhf.designpatternlib.mediator.Colleague2;
import com.lhf.designpatternlib.mediator.IMediator;
import com.lhf.designpatternlib.mediator.Mediator;
import com.lhf.designpatternlib.memento.Caretaker;
import com.lhf.designpatternlib.memento.Originator;
import com.lhf.designpatternlib.observer.Observable;
import com.lhf.designpatternlib.observer.Observer1;
import com.lhf.designpatternlib.observer.Observer2;
import com.lhf.designpatternlib.prototype.Address;
import com.lhf.designpatternlib.prototype.DeepPrototype;
import com.lhf.designpatternlib.prototype.ShallowPrototype;
import com.lhf.designpatternlib.proxy.ISubject;
import com.lhf.designpatternlib.proxy.ProxySubject;
import com.lhf.designpatternlib.proxy.ProxySubject1;
import com.lhf.designpatternlib.proxy.RealSubject;
import com.lhf.designpatternlib.proxy.RealSubject1;
import com.lhf.designpatternlib.responsibility_chain.Handler;
import com.lhf.designpatternlib.responsibility_chain.Handler1;
import com.lhf.designpatternlib.responsibility_chain.Handler2;
import com.lhf.designpatternlib.singleton.Singleton1;
import com.lhf.designpatternlib.singleton.Singleton2;
import com.lhf.designpatternlib.singleton.Singleton3;
import com.lhf.designpatternlib.singleton.Singleton4;
import com.lhf.designpatternlib.state.ThreadContext;
import com.lhf.designpatternlib.strategy.Strategy1;
import com.lhf.designpatternlib.strategy.Strategy2;
import com.lhf.designpatternlib.strategy.StrategyContext;
import com.lhf.designpatternlib.template_method.HookConcreteClass;
import com.lhf.designpatternlib.visitor.Element1;
import com.lhf.designpatternlib.visitor.Element2;
import com.lhf.designpatternlib.visitor.ElementStructure;
import com.lhf.designpatternlib.visitor.IVisitor;
import com.lhf.designpatternlib.visitor.VisitorA;
import com.lhf.designpatternlib.visitor.VisitorB;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;


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
//        testCommand();
        // 责任链模式
//        testResponsibilityChain();
        // 状态模式
//        testState();
        // 观察者模式
//        testObserver();
        // 中介模式
//        testMediator();
        // 迭代器模式
//        testIterator();
        // 访问者模式
//        testVisitor();
        // 备忘录模式
//        testMemento();
        // 解释器模式
//        testInterpret();
    }

    @Test
    public void testInterpret() {
        String statement = "3 * 2 * 4 / 6 % 5";
        Calculator calculator = new Calculator();
        calculator.build(statement);
        int result = calculator.compute();
        System.out.println("MyClass.testInterpret: result = [" + result + "]");
    }

    @Test
    public void testMemento() {
        Originator originator = new Originator();
        Caretaker caretaker = new Caretaker();
        originator.setStatus("111111111111");
        // 保存到备忘录
        caretaker.setMemento(originator.createMemento());
        originator.setStatus("22222222222");
        // 从备忘录恢复
        originator.restoreStatus(caretaker.getMemento());
    }

    @Test
    public void testVisitor() {
        ElementStructure elementStructure = new ElementStructure();
        elementStructure.add(new Element1());
        elementStructure.add(new Element2());
        IVisitor visitor = new VisitorA();
        elementStructure.accept(visitor);
        System.out.println("-------------------");
        visitor = new VisitorB();
        elementStructure.accept(visitor);
    }

    @Test
    public void testIterator() {
        Aggregate aggregate = new Aggregate();
        aggregate.add(1);
        aggregate.add(2);
        aggregate.add(3);
        aggregate.add(4);
        IIterator iterator = aggregate.getIterator();
        System.out.println("aggregate datas: ");
        while (iterator.hasNext()) {
            iterator.next();
        }
        System.out.println("---------------");
        iterator.first();
    }

    @Test
    public void testMediator() {
        IMediator mediator = new Mediator();
        Colleague c1 = new Colleague1("Colleague1");
        Colleague c2 = new Colleague2("Colleague2");
        mediator.register(c1);
        mediator.register(c2);
        c1.send("I am a msg from c1");
        c2.send("I am a msg from c2");
    }

    @Test
    public void testObserver() {
        Observable observable = new Observable();
        Observer1 observer1 = new Observer1();
        Observer2 observer2 = new Observer2();
        observable.add(observer1);
        observable.add(observer2);
        observable.setData("data 111");
        System.out.println("----------remove observer1----------");
        observable.remove(observer1);
        observable.setData("data 222");
    }

    @Test
    public void testState() {
        ThreadContext context = new ThreadContext();
        context.start();
        context.getCPU();
        context.suspend();
        context.resume();
        context.getCPU();
        context.stop();
//        context.getCPU();// 当前状态不支持的操作抛异常
    }

    @Test
    public void testResponsibilityChain() {
        Handler handler1 = new Handler1();
        Handler handler2 = new Handler2();
        handler1.setNext(handler2);
        handler1.handleRequest(1);

    }

    @Test
    public void testCommand() {
        Invoker invoker = new Invoker(new Command1());
        invoker.call();
        System.out.println("-----------------------");
        invoker.setCommand(new Command2());
        invoker.call();
    }

    @Test
    public void testStrategy() {
        StrategyContext context = new StrategyContext(new Strategy1());
        context.strategyMethod();
        System.out.println("----------------");
        context.setStrategy(new Strategy2());
        context.strategyMethod();
    }

    @Test
    public void testTemplateMethod() {
        HookConcreteClass hookConcreteClass = new HookConcreteClass();
        hookConcreteClass.templateMethod();
    }

    /**
     * 组合模式
     */
    @Test
    public void testComposite() {
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
    @Test
    public void testFlyweight() {
        FlyweightFactory factory = new FlyweightFactory();
        IFlyweight a1 = factory.getFlyweight("a");
        IFlyweight a2 = factory.getFlyweight("a");
        IFlyweight a3 = factory.getFlyweight("a");
        IFlyweight b1 = factory.getFlyweight("b");
        IFlyweight b2 = factory.getFlyweight("b");
        System.out.println();
        a1.operation(new UnsharedStatus("1st a"));
        a2.operation(new UnsharedStatus("2nd a"));
        a3.operation(new UnsharedStatus("3rd a"));
        assertEquals(a1, a2);// 相同的key获取到的是同一个对象
        b1.operation(new UnsharedStatus("1st b"));
        b2.operation(new UnsharedStatus("2nd b"));
        assertEquals(b1, b2);
    }

    /**
     * 外观模式
     */
    @Test
    public void testFacade() {
        Facade facade = new Facade();
        facade.method();
    }

    /**
     * 装饰模式
     */
    @Test
    public void testDecorator() {
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
    @Test
    public void testBridge() {
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
    @Test
    public void testObjectAdapter() {
        Adaptee adaptee = new Adaptee();
        Target target = new ObjectAdapter(adaptee);
        target.request();
    }

    /**
     * 类适配器模式
     */
    @Test
    public void testClassAdapter() {
        Target target = new ClassAdapter();
        target.request();
    }

    /**
     * 代理模式
     */
    @Test
    public void testProxy() {
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
    @Test
    public void testBuilder() {
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
    @Test
    public void testDeepPrototype() {
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
        assertNotSame(deepPrototype1, deepPrototype2);
        System.out.println("MyClass.testDeepPrototype: (deepPrototype1 == deepPrototype2) = [" + (deepPrototype1 == deepPrototype2) + "]");
        // 深拷贝对于对象成员变量也可以进行拷贝
        assertNotSame(deepPrototype1.getAddress(), deepPrototype2.getAddress());
        System.out.println("MyClass.testDeepPrototype: (deepPrototype1.getAddress() == deepPrototype2.getAddress()) = [" + (deepPrototype1.getAddress() == deepPrototype2.getAddress()) + "]");
        System.out.println("MyClass.testDeepPrototype: deepPrototype1 = [" + deepPrototype1 + "]");
        System.out.println("MyClass.testDeepPrototype: deepPrototype2 = [" + deepPrototype2 + "]");
    }

    /**
     * 浅拷贝原型模式
     */
    @Test
    public void testShallowPrototype() {
        ShallowPrototype shallowPrototype1 = new ShallowPrototype();
        shallowPrototype1.setName("Joshua");
        shallowPrototype1.setAge(18);
        shallowPrototype1.setAddress(new Address("SH"));
        try {
            ShallowPrototype shallowPrototype2 = (ShallowPrototype) shallowPrototype1.clone();
            shallowPrototype2.setName("lhf");
            shallowPrototype2.setAge(20);
            shallowPrototype2.getAddress().setCity("ZJ");
            assertNotSame(shallowPrototype1, shallowPrototype2);
            System.out.println("MyClass.testShallowPrototype: (shallowPrototype1 == shallowPrototype2) = [" + (shallowPrototype1 == shallowPrototype2) + "]");
            // 浅拷贝对于对象成员变量无法进行拷贝，还是指向同一个对象地址
            assertSame(shallowPrototype1.getAddress(), shallowPrototype2.getAddress());
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
    @Test
    public void testAbstractFactory() {
        IAbstractFactory factory1 = new AbstractFactory1();
        IProductA productA1 = factory1.createProductA();
        productA1.show();
        assertTrue(productA1 instanceof ProductA1);
        IProductB productB1 = factory1.createProductB();
        productB1.show();
        assertTrue(productB1 instanceof ProductB1);

        IAbstractFactory factory2 = new AbstractFactory2();
        IProductA productA2 = factory2.createProductA();
        productA2.show();
        assertTrue(productA2 instanceof ProductA2);
        IProductB productB2 = factory2.createProductB();
        productB2.show();
        assertTrue(productB2 instanceof ProductB2);
    }

    /**
     * 简单工厂模式
     */
    @Test
    public void testSimpleFactory() {
        SimpleFactory factory = new SimpleFactory();

        IProductA product = factory.createProduct("A1");
        product.show();

        product = factory.createProduct("A2");
        product.show();
    }

    /**
     * 静态内部类单例模式
     */
    @Test
    public void testSingleton4() {
        System.out.println("MyClass.testSingleton4() called");
        Singleton4 s1 = Singleton4.getInstance();
        Singleton4 s2 = Singleton4.getInstance();
        System.out.println("MyClass.testSingleton4: s1 = [" + s1 + "], s2 = [" + s2 + "]");
        System.out.println("MyClass.testSingleton4: (s1 == s2) = [" + (s1 == s2) + "]");
    }

    /**
     * 双重锁懒汉单例模式
     */
    @Test
    public void testSingleton3() {
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
    @Test
    public void testSingleton2() {
        System.out.println("MyClass.testSingleton2() called");
        Singleton2 s1 = Singleton2.getInstance();
        Singleton2 s2 = Singleton2.getInstance();
        System.out.println("MyClass.testSingleton2: s1 = [" + s1 + "], s2 = [" + s2 + "]");
        System.out.println("MyClass.testSingleton2: (s1 == s2) = [" + (s1 == s2) + "]");
    }

    /**
     * 饿汉单例模式
     */
    @Test
    public void testSingleton1() {
        System.out.println("MyClass.testSingleton1() called");
        Singleton1 s1 = Singleton1.getInstance();
        Singleton1 s2 = Singleton1.getInstance();
        System.out.println("MyClass.testSingleton1: s1 = [" + s1 + "], s2 = [" + s2 + "]");
        System.out.println("MyClass.testSingleton1: (s1 == s2) = [" + (s1 == s2) + "]");
    }
}
