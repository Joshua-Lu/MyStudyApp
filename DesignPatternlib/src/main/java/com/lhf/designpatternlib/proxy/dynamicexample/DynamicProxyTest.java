package com.lhf.designpatternlib.proxy.dynamicexample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * 动态代理
 * <p>
 * Created by Joshua on 2021/2/7 0:34
 */
public class DynamicProxyTest {
    public static void main(String[] args) {
        new DynamicProxyTest().test();
    }

    private void test() {
        IPerson joshua = new PersonImpl();
        System.out.println("===================== ownerProxy ====================");
        IPerson ownerProxy = getOwnerProxy(joshua);
        ownerProxy.setName("joshua");
//        ownerProxy.setGeekRating(10);// owner 不能设自己的rating
        int geekRating = ownerProxy.getGeekRating();
        System.out.println("DynamicProxyTest.test: geekRating = [" + geekRating + "]");
        String name = ownerProxy.getName();
        System.out.println("DynamicProxyTest.test: name = [" + name + "]");

        System.out.println("===================== nonOwnerProxy ====================");
        IPerson nonOwnerProxy = getNonOwnerProxy(joshua);
        nonOwnerProxy.setGeekRating(10);
        int geekRating1 = nonOwnerProxy.getGeekRating();
        System.out.println("DynamicProxyTest.test: geekRating1 = [" + geekRating1 + "]");
//        nonOwnerProxy.setName("lhf");// set方法里nonOwner 只能调 setGeekRating，其他的都不能
    }

    private IPerson getOwnerProxy(IPerson person) {
        OwnerInvocationHandler ownerInvocationHandler = new OwnerInvocationHandler(person);
        return getPersonProxy(person, ownerInvocationHandler);
    }

    private IPerson getNonOwnerProxy(IPerson person) {
        NonOwnerInvocationHandler nonOwnerInvocationHandler = new NonOwnerInvocationHandler(person);
        return getPersonProxy(person, nonOwnerInvocationHandler);
    }

    private IPerson getPersonProxy(IPerson person, InvocationHandler invocationHandler) {
        //设置系统属性，保存动态生成的代理类，E:\Code\MyStudyApp\com\sun\proxy\$Proxy0.class
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        return (IPerson) Proxy.newProxyInstance(
                person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                invocationHandler);
    }
}
