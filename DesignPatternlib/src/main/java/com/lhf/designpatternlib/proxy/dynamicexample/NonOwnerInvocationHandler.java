package com.lhf.designpatternlib.proxy.dynamicexample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Joshua on 2021/2/7 0:15
 */
public class NonOwnerInvocationHandler implements InvocationHandler {
    IPerson person;

    public NonOwnerInvocationHandler(IPerson person) {
        this.person = person;
    }

    /**
     * 每次proxy的方法被调用，就会导致proxy调用handler的此方法
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String methodName = method.getName();
        if (methodName.startsWith("get")) {
            return method.invoke(person, args);
        } else if (methodName.equals("setGeekRating")) {
            return method.invoke(person, args);
        } else if (methodName.startsWith("set")) {
            // 不支持访问的方法，跑出异常
            throw new IllegalAccessException("the ONLY set method NonOwner can invoke is setGeekRating");
        }
        return null;
    }
}
