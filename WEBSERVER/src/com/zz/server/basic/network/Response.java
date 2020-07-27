package com.zz.server.basic.network;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Date;

public class Response {

    private BufferedWriter bw;
    private StringBuilder responseInfo;
    private StringBuilder content;
    private final String BLANK = " ";
    private  final String CRLF = "\r\n";

    public Response(){
    }

    public Response(Socket client){
        try {
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            responseInfo = new StringBuilder();
            content = new StringBuilder();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void http(String message){
        content.append(message).append(CRLF);
    }
    private void httpHead(){
        responseInfo.append("Date: ").append(new Date()).append(CRLF);
        responseInfo.append("Server: Apache").append(CRLF);
        responseInfo.append("Content-Type: ").append("text/html").append(CRLF);
        responseInfo.append("Content-Length: ").append(content.toString().getBytes().length).append(CRLF);
        responseInfo.append(CRLF);
    }
    public void pushToBrowser(int code){
        responseInfo.append("HTTP/1.1").append(BLANK).append(code).append("OK").append(CRLF);
        httpHead();
        responseInfo.append(content.toString());
        try {
            bw.write(responseInfo.toString());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
