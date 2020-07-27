package com.zz.user;

import com.zz.server.basic.network.Request;
import com.zz.server.basic.network.Response;
import com.zz.server.basic.servlet.Servlet;

public class LoginServlet implements Servlet {
    @Override
    public void Service(Request request, Response response) {
        System.out.println("登陆");
    }
}
