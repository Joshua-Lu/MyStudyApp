package com.lhf.designpatternlib.state;

/**
 * Created by Joshua on 2020/4/6 20:08.
 */
public abstract class ThreadState {
    protected String stateName;

    public ThreadState(String stateName) {
        this.stateName = stateName;
    }

    public void start(ThreadContext threadContext) {
        throw new UnsupportedOperationException("current state is: " + stateName);
    }

    public void getCPU(ThreadContext threadContext) {
        throw new UnsupportedOperationException("current state is: " + stateName);
    }

    public void suspend(ThreadContext threadContext) {
        throw new UnsupportedOperationException("current state is: " + stateName);
    }

    public void stop(ThreadContext threadContext) {
        throw new UnsupportedOperationException("current state is: " + stateName);
    }

    public void resume(ThreadContext threadContext) {
        throw new UnsupportedOperationException("current state is: " + stateName);
    }

    @Override
    public String toString() {
        return "ThreadState{" +
                "stateName='" + stateName + '\'' +
                '}';
    }
}
