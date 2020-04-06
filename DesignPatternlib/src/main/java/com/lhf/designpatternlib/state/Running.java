package com.lhf.designpatternlib.state;

/**
 * Created by Joshua on 2020/4/6 20:10.
 */
public class Running extends ThreadState {
    public Running() {
        stateName = "Running";
        System.out.println("Running.Running: stateName = [" + stateName + "]");
    }

    public void suspend(ThreadContext threadContext) {
        System.out.println("Running.suspend() called with: threadContext = [" + threadContext + "]");
        threadContext.setState(new Blocked());
    }

    public void stop(ThreadContext threadContext) {
        System.out.println("Running.stop() called with: threadContext = [" + threadContext + "]");
        threadContext.setState(new Dead());
    }
}
