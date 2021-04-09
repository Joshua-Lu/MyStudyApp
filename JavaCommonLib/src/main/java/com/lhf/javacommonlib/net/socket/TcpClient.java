package com.lhf.javacommonlib.net.socket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * 客户端 Socket
 * Created by Joshua on 2020/10/21 21:14
 */
class TcpClient {

    public static final int port = 6666;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", port);

            // 给服务器发送数据
            System.out.println("send data to server");
            OutputStream outputStream = socket.getOutputStream();
//            outputStream.write("hello server".getBytes());

            // 上传文件到服务器
            FileInputStream fileInputStream = new FileInputStream("D:\\lhf\\filetest\\zoro.jpg");
            int len;
            byte[] bytes = new byte[1024];
            // 读取本地输入流，写到网络输出流
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
            fileInputStream.close();

            // 关闭网络输出流，写入结束标志，否则服务端读取流时，读取不到结束标志
            socket.shutdownOutput();

            // 接收服务器返回的数据
            System.out.println("receive data from server");
            InputStream inputStream = socket.getInputStream();
            bytes = new byte[1024];
            while ((len = inputStream.read(bytes)) != -1) {
                System.out.println("TcpClient.main: data from server = [" + new String(bytes) + "]");
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
