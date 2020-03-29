package com.lhf.designpatternlib.proxy;

/**
 * 提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。
 * Created by Joshua on 2020/3/29 16:12.
 */
public class ProxySubject implements ISubject {

    private ISubject realSubject;

    public ProxySubject(ISubject realSubject) {
        this.realSubject = realSubject;
    }

    @Override
    public void request() {
        System.out.println("ProxySubject.request() called");
        preRequest();
        if (realSubject != null) {
            realSubject.request();
        }
        postRequest();
    }

    private void postRequest() {
        System.out.println("ProxySubject.postRequest() called");
    }

    private void preRequest() {
        System.out.println("ProxySubject.preRequest() called");
    }
}
