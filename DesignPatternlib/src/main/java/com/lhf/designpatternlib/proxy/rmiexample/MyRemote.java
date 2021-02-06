package com.lhf.designpatternlib.proxy.rmiexample;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 远程接口，继承 java.rmi.Remote 接口
 * <p>
 * Created by Joshua on 2021/2/5 21:30
 */
interface MyRemote extends Remote {
    // 客户端调用时，要通过网络，因此要声明 RemoteException
    // 变量和返回值都必须是 基本类型或实现Serializable
    String sayHello() throws RemoteException;
}
