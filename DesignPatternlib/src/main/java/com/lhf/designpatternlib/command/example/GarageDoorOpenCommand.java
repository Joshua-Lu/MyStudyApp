package com.lhf.designpatternlib.command.example;

import com.lhf.designpatternlib.command.ICommand;

/**
 * 车库门打开命令
 * <p>
 * Created by Joshua on 2021/1/24 17:13
 */
public class GarageDoorOpenCommand implements ICommand {
    GarageDoor garageDoor;

    public GarageDoorOpenCommand(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }


    @Override
    public void execute() {
        garageDoor.up();
    }

    @Override
    public void undo() {
        System.out.println("GarageDoorOpenCommand.undo() called 撤销车库门升起操作");
        garageDoor.down();
    }
}
