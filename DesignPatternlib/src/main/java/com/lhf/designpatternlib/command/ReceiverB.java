package com.lhf.designpatternlib.command;

/**
 * Created by Joshua on 2020/4/6 17:25.
 */
public class ReceiverB implements IReceiver {
    @Override
    public void action() {
        System.out.println("ReceiverB.action() called");
    }
}
