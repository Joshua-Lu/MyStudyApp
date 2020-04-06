package com.lhf.designpatternlib.state;

/**
 * Created by Joshua on 2020/4/6 20:10.
 */
public class Blocked extends ThreadState {
    public Blocked() {
        stateName = "Blocked";
        System.out.println("Blocked.Blocked: stateName = [" + stateName + "]");
    }

    public void resume(ThreadContext threadContext) {
        System.out.println("Blocked.resume() called with: threadContext = [" + threadContext + "]");
        threadContext.setState(new Runnable());
    }
}
