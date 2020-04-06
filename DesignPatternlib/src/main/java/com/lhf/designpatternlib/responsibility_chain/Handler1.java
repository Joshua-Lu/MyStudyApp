package com.lhf.designpatternlib.responsibility_chain;

/**
 * Created by Joshua on 2020/4/6 17:46.
 */
public class Handler1 extends Handler {
    @Override
    public void handleRequest(int request) {
        System.out.println("Handler1.handleRequest() called with: request = [" + request + "]");
        if (getNext() != null) {
            getNext().handleRequest(++request);
        }
    }
}
