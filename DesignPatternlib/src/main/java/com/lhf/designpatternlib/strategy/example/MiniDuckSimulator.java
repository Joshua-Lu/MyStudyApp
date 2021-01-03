package com.lhf.designpatternlib.strategy.example;

/**
 * Created by Joshua on 2021/1/3 23:06
 */
public class MiniDuckSimulator {
    public static void main(String[] args) {
        MallardDuck mallardDuck = new MallardDuck();
        mallardDuck.display();
        mallardDuck.performFly();
        mallardDuck.performQuack();

        System.out.println("=========================================");
        RubberDuck rubberDuck = new RubberDuck();
        rubberDuck.display();
        rubberDuck.performFly();
        rubberDuck.performQuack();

        System.out.println("====================改变橡皮鸭的飞行=====================");
        rubberDuck.setFlyBehavior(new FlyRocketPowered());
        rubberDuck.performFly();
    }
}
