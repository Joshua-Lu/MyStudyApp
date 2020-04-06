package com.lhf.designpatternlib.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介者模式
 * 定义一个中介对象来封装一系列对象之间的交互，使原有对象之间的耦合松散，且可以独立地改变它们之间的交互。
 * Created by Joshua on 2020/4/6 21:22.
 */
public class Mediator implements IMediator {
    private List<Colleague> colleagues = new ArrayList<Colleague>();

    @Override
    public void register(Colleague colleague) {
        if (!colleagues.contains(colleague)) {
            colleague.setMedium(this);
            colleagues.add(colleague);
        }
    }

    @Override
    public void relay(Colleague colleague) {
        String name = colleague.getName();
        for (Colleague c :
                colleagues) {
            if (!name.equals(c.getName())) {
                c.receive(name, colleague.getMsg());
            }
        }
    }
}
