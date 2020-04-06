package com.lhf.designpatternlib.command;

/**
 * 命令模式
 * 将一个请求封装为一个对象，使发出请求的责任和执行请求的责任分割开。
 * 这样两者之间通过命令对象进行沟通，这样方便将命令对象进行储存、传递、调用、增加与管理。
 * Created by Joshua on 2020/4/6 17:30.
 */
public class Invoker {
    private ICommand command;

    public void setCommand(ICommand command) {
        this.command = command;
    }

    public Invoker(ICommand command) {
        this.command = command;
    }

    public void call() {
        command.execute();
    }
}
