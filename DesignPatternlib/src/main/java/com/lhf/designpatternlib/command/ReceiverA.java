package com.lhf.designpatternlib.command;

/**
 * Created by Joshua on 2020/4/6 17:25.
 */
public class ReceiverA implements IReceiver {
    @Override
    public void action() {
        System.out.println("ReceiverA.action() called");
    }
}
