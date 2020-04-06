package com.lhf.designpatternlib.mediator;

/**
 * Created by Joshua on 2020/4/6 21:36.
 */
public class Colleague2 extends Colleague {
    public Colleague2(String name) {
        super(name);
    }

    @Override
    public void receive(String from, String msg) {
        System.out.println("Colleague2.receive() called with: from = [" + from + "], msg = [" + msg + "]");
    }

    @Override
    public void send(String msg) {
        System.out.println("Colleague2.send() called with: msg = [" + msg + "]");
        setMsg(msg);
        if (mediator != null) {
            mediator.relay(this);
        } else {
            System.out.println("Colleague2 is NOT register to mediator");
        }
    }
}
