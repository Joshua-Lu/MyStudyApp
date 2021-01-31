package com.lhf.designpatternlib.state;

/**
 * Created by Joshua on 2020/4/6 20:10.
 */
public class Blocked extends ThreadState {
    public Blocked() {
        super("Blocked");
        System.out.println("Blocked.Blocked: stateName = [" + stateName + "]");
    }

    @Override
    public void resume(ThreadContext threadContext) {
        System.out.println("Blocked.resume() called with: threadContext = [" + threadContext + "]");
        threadContext.setState(threadContext.getRunnableState());
    }
}
