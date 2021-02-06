package com.lhf.designpatternlib.proxy.rmiexample;

import java.rmi.Naming;

/**
 * 远程代理，运行客户前，必须先在命令行执行 rmiregistry，再运行MyRemoteImpl服务端，最后运行此客户端
 * Created by Joshua on 2021/2/6 17:26
 */
public class MyRemoteClient {
    public static void main(String[] args) {
        try {
            // 客户端获取stub对象，该对象通过网络调用实际的服务对象
            MyRemote service = (MyRemote) Naming.lookup("rmi://127.0.0.1/" + MyRemoteImpl.NAME_REMOTE_HELLO);
            // 获取到stub对象后，就可以像调用本地对象方法一样，调用远程对象方法
            String s = service.sayHello();
            System.out.println("MyRemoteClient.main: s = [" + s + "]");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
