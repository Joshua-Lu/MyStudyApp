package com.lhf.designpatternlib.state;

/**
 * Created by Joshua on 2020/4/6 20:10.
 */
public class New extends ThreadState {
    public New() {
        stateName = "New";
        System.out.println("New.New: stateName = [" + stateName + "]");
    }

    public void start(ThreadContext threadContext) {
        System.out.println("New.start() called with: threadContext = [" + threadContext + "]");
        threadContext.setState(new Runnable());
    }
}
