package com.lhf.designpatternlib.state;

/**
 * Created by Joshua on 2020/4/6 20:10.
 */
public class Running extends ThreadState {
    public Running() {
        super("Running");
        System.out.println("Running.Running: stateName = [" + stateName + "]");
    }

    @Override
    public void suspend(ThreadContext threadContext) {
        System.out.println("Running.suspend() called with: threadContext = [" + threadContext + "]");
        threadContext.setState(threadContext.getBlockedState());
    }

    @Override
    public void stop(ThreadContext threadContext) {
        System.out.println("Running.stop() called with: threadContext = [" + threadContext + "]");
        threadContext.setState(threadContext.getDeadState());
    }
}
