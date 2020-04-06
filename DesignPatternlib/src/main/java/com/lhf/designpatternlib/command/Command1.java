package com.lhf.designpatternlib.command;

/**
 * Created by Joshua on 2020/4/6 17:22.
 */
public class Command1 implements ICommand {

    private IReceiver receiver = new ReceiverA();

    @Override
    public void execute() {
        receiver.action();
    }
}
