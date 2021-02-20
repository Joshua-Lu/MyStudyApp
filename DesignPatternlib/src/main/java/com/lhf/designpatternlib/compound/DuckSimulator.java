package com.lhf.designpatternlib.compound;

/**
 * Created by Joshua on 2021/2/21 0:26
 */
public class DuckSimulator {
    public static void main(String[] args) {
        AbstractDuckFactory factory = new CountingDuckFactory();// 工厂模式、装饰者模式QuackCounter
        new DuckSimulator().simulate(factory);
    }

    private void simulate(AbstractDuckFactory factory) {
        System.out.println("DuckSimulator.simulate() called");
        Quackable mallardDuck = factory.createMallardDuck();
        Quackable redheadDuck = factory.createRedheadDuck();
        Quackable duckCall = factory.createDuckCall();
        Quackable rubberDuck = factory.createRubberDuck();
        DuckFactory duckFactory = new DuckFactory();
        Quackable gooseDuck = duckFactory.createGooseDuck();// 适配器模式GooseAdapter

        Flock flock = new Flock();// 组合模式
        flock.add(mallardDuck);
        flock.add(redheadDuck);
        flock.add(duckCall);
        flock.add(rubberDuck);
        flock.add(gooseDuck);

        Flock mallardFlock = new Flock();
        mallardFlock.add(factory.createMallardDuck());
        mallardFlock.add(factory.createMallardDuck());
        mallardFlock.add(factory.createMallardDuck());
        mallardFlock.add(factory.createMallardDuck());

        flock.add(mallardFlock);

//        simulate(mallardDuck);
//        simulate(redheadDuck);
//        simulate(duckCall);
//        simulate(rubberDuck);
//        simulate(gooseDuck);

        simulate(flock);
        System.out.println("=========================================");
        simulate(mallardFlock);

        System.out.println("DuckSimulator.simulate: QuackCounter.getNumberOfQuacks() = [" + QuackCounter.getNumberOfQuacks() + "]");


        System.out.println("====================== 测试观察者模式 ===================");
        System.out.println("====================== 这只鸭子不记次数 ===================");
        Quackable normalMallardDuck = duckFactory.createMallardDuck();
        normalMallardDuck.registerObserver(new Observer());// 观察者模式
        normalMallardDuck.quack();
        System.out.println("DuckSimulator.simulate: QuackCounter.getNumberOfQuacks() = [" + QuackCounter.getNumberOfQuacks() + "]");

        System.out.println("====================== 这只鸭子记次数 ===================");
        mallardDuck.registerObserver(new Observer());
        mallardDuck.quack();
        gooseDuck.registerObserver(new Observer());
        gooseDuck.quack();
        System.out.println("DuckSimulator.simulate: QuackCounter.getNumberOfQuacks() = [" + QuackCounter.getNumberOfQuacks() + "]");

        System.out.println("====================== 测试观察绿头鸭群 ===================");
        mallardFlock.registerObserver(new Observer());
        mallardFlock.quack();
        System.out.println("DuckSimulator.simulate: QuackCounter.getNumberOfQuacks() = [" + QuackCounter.getNumberOfQuacks() + "]");

        System.out.println("====================== 测试观察全部鸭群 ===================");
        flock.registerObserver(new Observer());
        flock.quack();
        System.out.println("DuckSimulator.simulate: QuackCounter.getNumberOfQuacks() = [" + QuackCounter.getNumberOfQuacks() + "]");


    }

    private void simulate(Quackable quackable) {
        quackable.quack();
    }
}
