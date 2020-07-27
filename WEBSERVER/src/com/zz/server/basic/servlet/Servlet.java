package com.zz.server.basic.servlet;

import com.zz.server.basic.network.Request;
import com.zz.server.basic.network.Response;

public interface Servlet {
    public void Service(Request request,Response response);
}
