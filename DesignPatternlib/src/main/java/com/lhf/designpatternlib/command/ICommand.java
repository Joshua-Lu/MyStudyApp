package com.lhf.designpatternlib.command;

/**
 * Created by Joshua on 2020/4/6 17:22.
 */
public interface ICommand {
    void execute();

    default void undo() {
        System.out.println(getClass().getSimpleName() + ".undo() called");
    }
}
