package com.lhf.designpatternlib.command.example;

/**
 * 车库门
 * <p>
 * Created by Joshua on 2021/1/24 17:11
 */
public class GarageDoor {
    public void up() {
        System.out.println("GarageDoor.up() called 车库门升起");
    }

    public void down() {
        System.out.println("GarageDoor.down() called 车库门降下");
    }
}
