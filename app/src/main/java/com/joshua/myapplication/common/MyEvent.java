package com.joshua.myapplication.common;

/**
 * @author Joshua
 * @date 2021/4/18 17:26
 */
public class MyEvent {
    String msg;

    public MyEvent(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MyEvent{" +
                "msg='" + msg + '\'' +
                '}';
    }
}
