# DesignPatternLib独立Module
 常用设计模式简单例子   
## 创建型模式
1. 单例模式 Singleton Pattern    

   四种类型：饿汉模式、懒汉模式（线程不安全）、双重锁懒汉模式、静态内部类模式

2. 工厂模式 Factory Pattern    

   三种类型：简单工厂模式、工厂方法模式、抽象工厂模式

   ![抽象工厂模式类图](README.assets/factory.png)

3. 建造者模式 Builder Pattern  

   ![建造者模式类图](README.assets\builder.png)

4. 原型模式 Prototype Pattern

   
## 结构型模式
5. 代理模式 Proxy Pattern  

   ![代理模式类图](README.assets\proxy.png)

6. 适配器模式 Adapter Pattern    

   两种类型：  

   - 类适配器模式  

   ![类适配器模式类图](README.assets\class_adapter.png)

   - 对象适配器模式

     ![对象适配器模式类图](README.assets\object_adapter.png)

7. 桥接模式 Bridge Pattern   

   ![桥接模式类图](README.assets\bridge.png)

8. 装饰模式 Decorator Pattern

   ![装饰模式类图](README.assets\decorator.png)

9. 外观模式 Facade Pattern

   ![外观模式类图](README.assets\facade.png)

10. 享元模式 Flyweight Pattern

    ![享元模式类图](README.assets\flyweight.png)

11. 组合模式 Composite Pattern

    ![组合模式类图](README.assets\composite.png)
## 行为型模式
12. 模板方法模式 Template Method Pattern  

    1. JDK中的应用：  

    - Arrays.sort()：sort()为模板方法，定义了排序算法，但里面的具体排序规则是Comparable.compareTo()方法，该方法由排序的元素去实现。（该方法不是严格意义上的模板方法模式，它没有使用子类继承，但思想一样，用一个类的方法填补模板方法中的一个方法的实现）。
    - InputStream.read()：该方法是由子类（如FileInputStream、ObjectInputStream等）去实现的，而这个方法又会被read(byte b[], int off, int len)模板方法调用。

13. 策略模式 Strategy Pattern

    ![策略模式类图](README.assets\strategy.png)

14. 命令模式 Command Pattern  

    ![命令模式类图](README.assets\command.png)

15. 责任链模式 Chain of Responsibility Pattern

    ![责任链模式类图](README.assets\responsibility_chain.png)

16. 状态模式 State Pattern

    ![状态模式类图](README.assets\state.png)

17. 观察者模式 Observer Pattern

    ![观察者模式类图](README.assets\observer.png)

18. 中介者模式 Mediator Pattern

    ![中介者模式类图](README.assets\mediator.png)

19. 迭代器模式 Iterator Pattern

    ![迭代器模式类图](README.assets\iterator.png)

20. 访问者模式 Visitor Pattern

    ![访问者模式类图](README.assets\visitor.png)

21. 备忘录模式 Memento Pattern

    ![备忘录模式类图](README.assets\memento.png)

22. 解释器模式 Interpreter Pattern

    ![解释器模式类图](README.assets\interpret.png)

 