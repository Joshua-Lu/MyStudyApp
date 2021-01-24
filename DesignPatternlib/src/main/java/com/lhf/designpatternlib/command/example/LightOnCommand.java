package com.lhf.designpatternlib.command.example;

import com.lhf.designpatternlib.command.ICommand;

/**
 * 电灯打开命令
 * <p>
 * Created by Joshua on 2021/1/24 17:00
 */
public class LightOnCommand implements ICommand {

    Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        System.out.println("LightOnCommand.undo() called 撤销开灯操作");
        light.off();
    }
}
