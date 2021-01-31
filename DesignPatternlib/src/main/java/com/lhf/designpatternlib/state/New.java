package com.lhf.designpatternlib.state;

/**
 * Created by Joshua on 2020/4/6 20:10.
 */
public class New extends ThreadState {
    public New() {
        super("New");
        System.out.println("New.New: stateName = [" + stateName + "]");
    }

    @Override
    public void start(ThreadContext threadContext) {
        System.out.println("New.start() called with: threadContext = [" + threadContext + "]");
        threadContext.setState(threadContext.getRunnableState());
    }
}
