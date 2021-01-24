package com.lhf.designpatternlib.command.example;

/**
 * 电灯
 * <p>
 * Created by Joshua on 2021/1/24 16:59
 */
public class Light {
    public void on() {
        System.out.println("Light.on() called 打开电灯");
    }

    public void off() {
        System.out.println("Light.off() called 关闭电灯");
    }
}
