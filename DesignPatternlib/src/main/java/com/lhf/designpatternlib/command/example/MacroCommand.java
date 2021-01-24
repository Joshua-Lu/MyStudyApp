package com.lhf.designpatternlib.command.example;

import com.lhf.designpatternlib.command.ICommand;

/**
 * 宏命令，可以执行一组命令
 * <p>
 * Created by Joshua on 2021/1/24 18:44
 */
public class MacroCommand implements ICommand {

    ICommand[] commands;

    public MacroCommand(ICommand[] commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println("MacroCommand.execute() called 宏命令执行：");
        for (ICommand command : commands) {
            command.execute();
        }
    }

    @Override
    public void undo() {
        for (ICommand command : commands) {
            command.undo();
        }
    }
}
