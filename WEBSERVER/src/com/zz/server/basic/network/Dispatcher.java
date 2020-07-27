package com.zz.server.basic.network;

import com.zz.server.basic.servlet.Servlet;
import com.zz.server.basic.servlet.WebApp;

import java.io.IOException;
import java.net.Socket;

public class Dispatcher implements Runnable{
    private Socket client;
    private Request request;
    private Response response;
    public Dispatcher(Socket client){
        this.client = client;
        request = new Request(client);
        response = new Response(client);
    }
    @Override
    public void run() {
        System.out.println("连接已建立");
        String servletName = request.getUrl();
        Servlet servlet = WebApp.getServletByUrl(servletName);
        servlet.Service(request,response);
        if(servlet!=null)
        response.pushToBrowser(200);
        else response.pushToBrowser(404);
        release();//短连接 用完释放
    }
    private void release(){
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
