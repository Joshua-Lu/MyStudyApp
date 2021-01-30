package com.lhf.designpatternlib.composite.example;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Joshua on 2021/1/30 22:47
 */
public class Menu implements MenuComponent {

    private ArrayList<MenuComponent> menus;
    private String name;

    public Menu(String name) {
        this.name = name;
        menus = new ArrayList<>();
    }

    @Override
    public void add(MenuComponent menuComponent) {
        menus.add(menuComponent);
    }

    @Override
    public void remove(MenuComponent menuComponent) {
        menus.remove(menuComponent);
    }

    @Override
    public MenuComponent getChild(int i) {
        return menus.get(i);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menus=" + menus +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void print() {
        System.out.println("Menu.print: name = [" + name + "]");
        System.out.println("=========================================");
        for (MenuComponent menu : menus) {
            menu.print();
        }
    }

    @Override
    public Iterator<MenuComponent> createIterator() {
        return menus.iterator();
    }
}
