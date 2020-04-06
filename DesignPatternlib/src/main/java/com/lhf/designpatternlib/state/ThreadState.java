package com.lhf.designpatternlib.state;

/**
 * Created by Joshua on 2020/4/6 20:08.
 */
public abstract class ThreadState {
    protected String stateName;

    @Override
    public String toString() {
        return "ThreadState{" +
                "stateName='" + stateName + '\'' +
                '}';
    }
}
