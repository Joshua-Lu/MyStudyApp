package com.lhf.designpatternlib.memento;

/**
 * Created by Joshua on 2020/4/6 23:08.
 */
public class Originator {
    private String state;

    public void setState(String state) {
        System.out.println("Originator.setState() called with: state = [" + state + "]");
        this.state = state;
    }

    public String getState() {
        System.out.println("Originator.getState: state = [" + state + "]");
        return state;
    }

    public Memento createMemento() {
        System.out.println("Originator.createMemento: state = [" + state + "]");
        return new Memento(state);
    }

    public void restoreMemento(Memento m) {
        System.out.println("Originator.restoreMemento() called with: m = [" + m + "]");
        this.setState(m.getState());
    }

    @Override
    public String toString() {
        return "Originator{" +
                "state='" + state + '\'' +
                '}';
    }
}
