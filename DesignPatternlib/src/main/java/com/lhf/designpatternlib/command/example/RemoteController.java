package com.lhf.designpatternlib.command.example;

import com.lhf.designpatternlib.command.ICommand;

import java.util.Stack;

/**
 * 遥控器
 * <p>
 * Created by Joshua on 2021/1/24 17:02
 */
public class RemoteController {

    private ICommand[] onCommands;
    private ICommand[] offCommands;
    // 插槽数量（命令的数量）
    private static final int SLOT_COUNT = 7;
    // 用来记录最后一次执行的命令，实现撤销操作
    private ICommand undoCommand;
    // 替换上面只能记录最后一次执行的undoCommand，
    // 使用栈Stack记录执行的命令，可以实现多层次的撤销操作
    private Stack<ICommand> undoStack;

    public RemoteController() {
        onCommands = new ICommand[SLOT_COUNT];
        offCommands = new ICommand[SLOT_COUNT];

        // 初始化命令,防止出现空指针
        NoCommand noCommand = new NoCommand();
        for (int i = 0; i < SLOT_COUNT; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
        undoCommand = noCommand;
        undoStack = new Stack<>();
    }

    public void setCommand(int slot, ICommand onCommand, ICommand offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPressed(int slot) {
        onCommands[slot].execute();
        undoCommand = onCommands[slot];
        undoStack.push(undoCommand);
    }

    public void offButtonWasPressed(int slot) {
        offCommands[slot].execute();
        undoCommand = offCommands[slot];
        undoStack.push(undoCommand);
    }

    public void undoButtonWasPressed() {
//        undoCommand.undo();
        System.out.println("RemoteController.undoButtonWasPressed: undoStack.size() = [" + undoStack.size() + "]");
        System.out.println("RemoteController.undoButtonWasPressed: undoStack = [" + undoStack.toString() + "]");
        if (!undoStack.empty()) {
            undoStack.pop().undo();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n----------------remote controller-------------\n");
        for (int i = 0; i < SLOT_COUNT; i++) {
            sb.append("slot ").append(i).append(": ")
                    .append(onCommands[i].getClass().getSimpleName()).append(", ")
                    .append(offCommands[i].getClass().getSimpleName()).append("\n");
        }
        sb.append("undoCommand: ").append(undoCommand.getClass().getSimpleName()).append("\n");
        return sb.toString();
    }
}
