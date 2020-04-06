package com.lhf.designpatternlib.mediator;

/**
 * Created by Joshua on 2020/4/6 21:12.
 */
public abstract class Colleague {
    private String name;
    private String msg;
    protected IMediator mediator;

    public Colleague(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setMedium(IMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void receive(String from, String msg);

    public abstract void send(String msg);
}
