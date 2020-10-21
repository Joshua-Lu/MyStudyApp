package com.lhf.javacommonlib.net;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务端 ServerSocket
 * Created by Joshua on 2020/10/21 21:20
 */
class TcpServer {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(TcpClient.port);
            System.out.println("wait for client");
            Socket socket = server.accept();

            // 接收客户端发送过来的数据
            System.out.println("receive data from client");
            InputStream inputStream = socket.getInputStream();
            int len;
            byte[] bytes = new byte[1024];
            File file = new File("D:\\lhf\\upload");
            if (!file.exists()) {
                file.mkdirs();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file + "\\" + "picFromClient.jpg");
            while ((len = inputStream.read(bytes)) != -1) {
//                System.out.println("TcpServer.main: data from client = [" + new String(bytes) + "]");
                fileOutputStream.write(bytes, 0, len);
            }

            // 给客户端返回数据
            System.out.println("send data to client");
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hello client".getBytes());

            socket.close();
            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
