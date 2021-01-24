package com.lhf.designpatternlib.command.example;

import com.lhf.designpatternlib.command.ICommand;

/**
 * 车库门关闭命令
 * <p>
 * Created by Joshua on 2021/1/24 17:13
 */
public class GarageDoorCloseCommand implements ICommand {
    GarageDoor garageDoor;

    public GarageDoorCloseCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }


    @Override
    public void execute() {
        garageDoor.down();
    }

    @Override
    public void undo() {
        System.out.println("GarageDoorCloseCommand.undo() called 撤销车库门降下操作");
        garageDoor.up();
    }
}
