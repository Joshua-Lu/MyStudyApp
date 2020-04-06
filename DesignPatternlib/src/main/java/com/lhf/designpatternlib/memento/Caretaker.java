package com.lhf.designpatternlib.memento;

/**
 * Created by Joshua on 2020/4/6 23:08.
 */
public class Caretaker {
    private Memento memento;

    public void setMemento(Memento m) {
        System.out.println("Caretaker.setMemento() called with: m = [" + m + "]");
        memento = m;
    }

    public Memento getMemento() {
        System.out.println("Caretaker.getMemento: memento = [" + memento + "]");
        return memento;
    }
}
