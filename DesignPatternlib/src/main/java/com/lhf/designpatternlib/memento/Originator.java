package com.lhf.designpatternlib.memento;

/**
 * Created by Joshua on 2020/4/6 23:08.
 */
public class Originator {
    private String status;

    public void setStatus(String status) {
        System.out.println("Originator.setStatus() called with: status = [" + status + "]");
        this.status = status;
    }

    public String getStatus() {
        System.out.println("Originator.getStatus: status = [" + status + "]");
        return status;
    }

    public Memento createMemento() {
        System.out.println("Originator.createMemento: status = [" + status + "]");
        return new Memento(status);
    }

    public void restoreStatus(Memento m) {
        System.out.println("Originator.restoreStatus() called with: m = [" + m + "]");
        this.setStatus(m.getState());
    }

    @Override
    public String toString() {
        return "Originator{" +
                "status='" + status + '\'' +
                '}';
    }
}
