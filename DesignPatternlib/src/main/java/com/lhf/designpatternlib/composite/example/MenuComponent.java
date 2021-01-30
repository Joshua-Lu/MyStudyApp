package com.lhf.designpatternlib.composite.example;

import java.util.Iterator;

/**
 * Created by Joshua on 2021/1/30 22:41
 */
interface MenuComponent {
    // 以下是Menu相关操作
    default void add(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    default void remove(MenuComponent menuComponent) {
        throw new UnsupportedOperationException();
    }

    default MenuComponent getChild(int i) {
        throw new UnsupportedOperationException();
    }

    // 以下是MenuItem相关操作
    default String getName() {
        throw new UnsupportedOperationException();
    }

    default int getPrice() {
        throw new UnsupportedOperationException();
    }

    // 两者都要实现的方法
    void print();

    Iterator<MenuComponent> createIterator();
}
