package com.lhf.designpatternlib.composite.example;

import java.util.Iterator;
import java.util.Stack;

/**
 * 遍历组合模式的树形结构
 * <p>
 * Created by Joshua on 2021/1/30 23:54
 */
public class CompositeIterator implements Iterator<MenuComponent> {

    Stack<Iterator<MenuComponent>> stack;

    public CompositeIterator(Iterator<MenuComponent> iterator) {
        stack = new Stack<>();
        stack.push(iterator);
    }

    @Override
    public boolean hasNext() {
        if (!stack.isEmpty()) {
            Iterator<MenuComponent> iterator = stack.peek();
            if (iterator.hasNext()) {
                return true;
            } else {
                stack.pop();
                return hasNext();
            }
        }
        return false;
    }

    @Override
    public MenuComponent next() {
        if (hasNext()) {
            Iterator<MenuComponent> iterator = stack.peek();
            MenuComponent next = iterator.next();
            if (next instanceof Menu) {
                stack.push(next.createIterator());
            }
            return next;
        }
        return null;
    }
}
