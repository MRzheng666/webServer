package com.zz.server.basic.network;

import com.zz.server.basic.servlet.Servlet;
import com.zz.server.basic.servlet.WebApp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
    private ServerSocket serverSocket;
    private boolean isrun;
    public static void main(String[] args) {
        MyServer server = new MyServer();
        server.start();

    }
    //启动服务
    public void start(){
        try {
            serverSocket = new ServerSocket(8888); //端口
            isrun = true;
            revice();
        } catch (IOException e) {
            System.out.println("服务启动失败");
        }

    }
    //接受连接
    public void revice(){
        while (true){
            try {
                new Thread(new Dispatcher(serverSocket.accept())).start();
            } catch (IOException e) {
                System.out.println("客户端错误");
            }
        }
    }
    //停止服务
    public void stop(){
        isrun = false;
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
