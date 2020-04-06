package com.lhf.designpatternlib.memento;

/**
 * 备忘录模式
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，以便以后当需要时能将该对象恢复到原先保存的状态。
 * 该模式又叫快照模式。
 * Created by Joshua on 2020/4/6 23:07.
 */
public class Memento {
    private String state;

    public Memento(String state) {
        System.out.println("Memento.Memento() called with: state = [" + state + "]");
        this.state = state;
    }

    public void setState(String state) {
        System.out.println("Memento.setState() called with: state = [" + state + "]");
        this.state = state;
    }

    public String getState() {
        System.out.println("Memento.getState: state = [" + state + "]");
        return state;
    }

    @Override
    public String toString() {
        return "Memento{" +
                "state='" + state + '\'' +
                '}';
    }
}
