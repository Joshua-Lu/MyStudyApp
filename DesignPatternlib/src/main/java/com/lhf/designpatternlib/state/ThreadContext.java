package com.lhf.designpatternlib.state;

/**
 * 状态模式
 * 对有状态的对象，把复杂的“判断逻辑”提取到不同的状态对象中，允许状态对象在其内部状态发生改变时改变其行为。
 * Created by Joshua on 2020/4/6 20:16.
 */
public class ThreadContext {
    private ThreadState state;

    public ThreadState getState() {
        return state;
    }

    public void setState(ThreadState state) {
        this.state = state;
    }

    public ThreadContext() {
        state = new New();
    }

    public void start() {
        if (state instanceof New) {
            ((New) state).start(this);
        } else {
            System.out.println("state is NOT instanceof New, can NOT invoke method start()");
        }
    }

    public void getCPU() {
        if (state instanceof Runnable) {
            ((Runnable) state).getCPU(this);
        } else {
            System.out.println("state is NOT instanceof Runnable, can NOT invoke method getCPU()");
        }
    }

    public void suspend() {
        if (state instanceof Running) {
            ((Running) state).suspend(this);
        } else {
            System.out.println("state is NOT instanceof Running, can NOT invoke method suspend()");
        }
    }

    public void stop() {
        if (state instanceof Running) {
            ((Running) state).stop(this);
        } else {
            System.out.println("state is NOT instanceof Running, can NOT invoke method stop()");
        }
    }

    public void resume() {
        if (state instanceof Blocked) {
            ((Blocked) state).resume(this);
        } else {
            System.out.println("state is NOT instanceof Blocked, can NOT invoke method resume()");
        }
    }

    @Override
    public String toString() {
        return "ThreadContext{" +
                "state=" + state +
                '}';
    }
}
