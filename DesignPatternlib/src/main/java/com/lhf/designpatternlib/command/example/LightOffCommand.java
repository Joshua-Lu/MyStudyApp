package com.lhf.designpatternlib.command.example;

import com.lhf.designpatternlib.command.ICommand;

/**
 * 电灯关闭命令
 * <p>
 * Created by Joshua on 2021/1/24 17:00
 */
public class LightOffCommand implements ICommand {

    Light light;

    public LightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.off();
    }

    @Override
    public void undo() {
        System.out.println("LightOffCommand.undo() called 撤销关灯操作");
        light.on();
    }
}
