package com.lhf.designpatternlib.command.example;

import com.lhf.designpatternlib.command.ICommand;

/**
 * 无命令默认实现
 * <p>
 * Created by Joshua on 2021/1/24 17:33
 */
public class NoCommand implements ICommand {
    @Override
    public void execute() {
        System.out.println("NoCommand.execute() called");
    }
}
