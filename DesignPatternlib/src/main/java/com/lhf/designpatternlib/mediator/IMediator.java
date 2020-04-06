package com.lhf.designpatternlib.mediator;

/**
 * Created by Joshua on 2020/4/6 21:11.
 */
public interface IMediator {
    void register(Colleague colleague);// 注册

    void relay(Colleague colleague); //转发
}
