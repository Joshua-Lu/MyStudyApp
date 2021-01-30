package com.lhf.designpatternlib.composite.example;

import java.util.Iterator;

/**
 * Created by Joshua on 2021/1/30 22:57
 */
public class MenuItem implements MenuComponent {

    private String name;
    private int price;

    public MenuItem(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public void print() {
        System.out.println(this);
    }

    @Override
    public Iterator<MenuComponent> createIterator() {
        return new NullIterator();
    }

    /**
     * 作用是“没作用”的迭代器，可以避免客户端调用时判空，
     * 跟命令模式的 NoCommand 类似
     */
    private static class NullIterator implements Iterator<MenuComponent> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public MenuComponent next() {
            return null;
        }
    }
}
