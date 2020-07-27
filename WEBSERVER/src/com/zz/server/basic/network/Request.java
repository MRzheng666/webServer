package com.zz.server.basic.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Request {
    private InputStream is;
    private String method;
    private String requestInfo;
    private String url;
    private Map<String,String> map;
    private final String BLANK = " ";
    private  final String CRLF = "\r\n";

    public Request(){

    }
    public Request(Socket client){
        try {
            is = client.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        getHttp();
        getMethod();
        getUrl();
        createMap();
    }
    private void getHttp(){
        byte[] datas = new byte[1024*1024];
        int len = 0;
        try {
            len = is.read(datas);
        } catch (IOException e) {
            e.printStackTrace();
        }
        requestInfo = new String(datas,0,len);
    }
    public String getMethod(){
        method = requestInfo.substring(0,requestInfo.indexOf('/')).trim().toLowerCase();
        return method;
    }
    public String getUrl(){
    //    System.out.println(requestInfo);
        int start = requestInfo.indexOf("/");
        int end = requestInfo.indexOf("HTTP");
        url = requestInfo.substring(start+1,end-1);
        return url;
    }
    private void createMap(){
        map = new HashMap<>();
        if(url.contains("?")){
            int start = url.indexOf("?");
            String s = url.substring(start+1);
         //   System.out.println(s);
            String[] ss = s.split("&");
            for(String sss:ss){
                String[]mapvalue = sss.split("=");
                map.put(mapvalue[0],mapvalue[1]);
            }
        }
        //如果是post在请求体中
        String s = requestInfo.substring(requestInfo.lastIndexOf(CRLF)).trim();
        if(s!=null&&s.length()>0){
            String[] ss = s.split("&");
            for(String sss:ss){
                String[]mapvalue = sss.split("=");
                map.put(mapvalue[0],mapvalue[1]);
            }
        }
    }
    public String getParameter(String key){
        if(map.containsKey(key)) return map.get(key);
        else return null;
    }
}
