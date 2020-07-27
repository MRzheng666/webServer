package com.zz.server.basic.servlet;


import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.lang.reflect.InvocationTargetException;

public class WebApp {
    private  static WebContext webContext;
    static {
        //1.或去SAXParserFactory实例
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //2.获取SAXparser实例
        SAXParser saxParser = null;
        try {
            saxParser = factory.newSAXParser();
            //创建Handel对象
            SaxHandel handel = new SaxHandel();
            saxParser.parse(Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream("com/zz/server/basic/servlet/web.xml"),handel);
            webContext = new WebContext(handel.getEntities(),handel.getMappings());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Servlet getServletByUrl(String url){

        String name = webContext.getMappingMap().get(url);
        String servletC = webContext.getEntityMap().get(name);
        System.out.println(name);
        try {
            Class clazz = Class.forName(servletC);
            Servlet servlet = (Servlet) clazz.getConstructor().newInstance();
            return servlet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
