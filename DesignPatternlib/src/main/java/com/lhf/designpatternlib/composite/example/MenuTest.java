package com.lhf.designpatternlib.composite.example;

import java.util.Iterator;

/**
 * Created by Joshua on 2021/1/30 23:34
 */
public class MenuTest {
    public static void main(String[] args) {
        MenuComponent allMenu = new Menu("全部菜单");

        MenuComponent breakfastMenu = new Menu("早餐菜单");
        MenuComponent lunchMenu = new Menu("午餐菜单");
        MenuComponent afternoonMenu = new Menu("下午茶菜单");
        MenuComponent dinnerMenu = new Menu("晚餐菜单");

        breakfastMenu.add(new MenuItem("包子", 2));
        breakfastMenu.add(new MenuItem("生煎", 3));
        breakfastMenu.add(new MenuItem("豆花", 4));

        lunchMenu.add(new MenuItem("西红柿炒蛋", 4));
        lunchMenu.add(new MenuItem("蒜蓉开背虾", 12));


        afternoonMenu.add(new MenuItem("红豆奶茶", 10));
        afternoonMenu.add(new MenuItem("咖啡", 12));

        dinnerMenu.add(new MenuItem("红烧排骨", 15));
        dinnerMenu.add(new MenuItem("肉蒸蛋", 10));
        dinnerMenu.add(new MenuItem("青菜", 2));

        lunchMenu.add(afternoonMenu);
        lunchMenu.add(new MenuItem("米饭", 1));
        allMenu.add(breakfastMenu);
        allMenu.add(lunchMenu);
        allMenu.add(dinnerMenu);

        Waitress waitress = new Waitress(allMenu);
//        waitress.print();

        System.out.println("==================== test CompositeIterator =====================");
        Iterator<MenuComponent> allMenuIterator = new CompositeIterator(allMenu.createIterator());
        while (allMenuIterator.hasNext()) {
            MenuComponent next = allMenuIterator.next();
            if (next instanceof MenuItem) {
                next.print();
            }
        }
    }
}
