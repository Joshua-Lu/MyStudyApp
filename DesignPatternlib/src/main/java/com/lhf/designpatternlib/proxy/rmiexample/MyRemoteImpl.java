package com.lhf.designpatternlib.proxy.rmiexample;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * 远程实现，实现MyRemote接口
 * 继承 java.rmi.server.UnicastRemoteObject ，成为远程服务对象
 * <p>
 * 重要：创建完该远程实现后，在D:\GitHub\MyStudyApp\DesignPatternlib\build\classes\java\main目录下，
 * 执行 rmic com.lhf.designpatternlib.proxy.rmiexample.MyRemoteImpl
 * 即可生成 MyRemoteImpl_Stub extends RemoteStub implements MyRemote, Remote
 * <p>
 * 然后执行 rmiregistry，再运行此服务端，最后运行MyRemoteClient客户端
 * <p>
 * Created by Joshua on 2021/2/5 21:39
 */
public class MyRemoteImpl extends UnicastRemoteObject implements MyRemote {

    public static final String NAME_REMOTE_HELLO = "RemoteHello";

    // UnicastRemoteObject 的构造方法，声明了 RemoteException，因此这里必须声明
    protected MyRemoteImpl() throws RemoteException {
    }

    @Override
    public String sayHello() throws RemoteException {
        return "Server says, 'Hey'";
    }

    public static void main(String[] args) {
        try {
            // 注册服务前，必须在命令行执行 rmiregistry
            MyRemote service = new MyRemoteImpl();
            Naming.rebind(NAME_REMOTE_HELLO, service);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
