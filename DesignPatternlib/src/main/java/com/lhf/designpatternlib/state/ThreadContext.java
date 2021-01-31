package com.lhf.designpatternlib.state;

/**
 * 状态模式
 * 对有状态的对象，把复杂的“判断逻辑”提取到不同的状态对象中，允许状态对象在其内部状态发生改变时改变其行为。
 * Created by Joshua on 2020/4/6 20:16.
 */
public class ThreadContext {
    ThreadState newState;
    ThreadState runnableState;
    ThreadState runningState;
    ThreadState blockedState;
    ThreadState deadState;

    private ThreadState state;

    public ThreadState getState() {
        return state;
    }

    public void setState(ThreadState state) {
        this.state = state;
    }

    public ThreadContext() {
        newState = new New();
        runnableState = new Runnable();
        runningState = new Running();
        blockedState = new Blocked();
        deadState = new Dead();
        state = newState;
    }

    public ThreadState getNewState() {
        return newState;
    }

    public ThreadState getRunnableState() {
        return runnableState;
    }

    public ThreadState getRunningState() {
        return runningState;
    }

    public ThreadState getBlockedState() {
        return blockedState;
    }

    public ThreadState getDeadState() {
        return deadState;
    }

    public void start() {
        state.start(this);
    }

    public void getCPU() {
        state.getCPU(this);
    }

    public void suspend() {
        state.suspend(this);
    }

    public void stop() {
        state.stop(this);
    }

    public void resume() {
        state.resume(this);
    }

    @Override
    public String toString() {
        return "ThreadContext{" +
                "state=" + state +
                '}';
    }
}
