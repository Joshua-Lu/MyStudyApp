package com.lhf.designpatternlib.composite.example;

/**
 * Created by Joshua on 2021/1/30 23:31
 */
public class Waitress {
    private MenuComponent allMenu;

    public Waitress(MenuComponent allMenu) {
        this.allMenu = allMenu;
    }

    public void print() {
        allMenu.print();
    }
}
