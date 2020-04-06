package com.lhf.designpatternlib.state;

/**
 * Created by Joshua on 2020/4/6 20:10.
 */
public class Runnable extends ThreadState {
    public Runnable() {
        stateName = "Runnable";
        System.out.println("Runnable.Runnable: stateName = [" + stateName + "]");
    }

    public void getCPU(ThreadContext threadContext) {
        System.out.println("Runnable.getCPU() called with: threadContext = [" + threadContext + "]");
        threadContext.setState(new Running());
    }
}
