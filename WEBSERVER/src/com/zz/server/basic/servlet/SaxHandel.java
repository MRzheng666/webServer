package com.zz.server.basic.servlet;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import java.util.ArrayList;
import java.util.List;

class SaxHandel extends DefaultHandler {
    private List<Entity> entities;
    private List<Mapping> mappings;
    private Entity entity;
    private Mapping mapping;
    private boolean isMapping;
    private String tag;

    @Override
    public void startDocument() throws SAXException {
        entities = new ArrayList<Entity>();
        mappings = new ArrayList<Mapping>();
        isMapping = false;
        System.out.println("sax解析开始");
    }

    //遍历xml文件结束标签
    @Override
    public void endDocument() throws SAXException {
        System.out.println("sax解析结束");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if(null!=qName){
            tag = qName;
            if (tag.equals("servlet")){
                entity = new Entity();
                isMapping = false;
            }else if (tag.equals("servlet-mapping")){
                mapping = new Mapping();
                isMapping = true;
            }
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if(null!=qName){
            if (qName.equals("servlet")){
                System.out.println("en 以壮如"+entity.getName()+" "+entity.getClz());
                entities.add(entity);

            }
            if(qName.equals("servlet-mapping")){
                mappings.add(mapping);
                System.out.println("以壮如"+mapping.getName()+" "+mapping.getPattern());
            }
            tag=null;
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch,start,length).trim();
        if(tag!=null){
            if(!isMapping){
                if(tag.equals("servlet-name")){
                    System.out.println(value);
                    entity.setName(value);
                    System.out.println(entity.getName());
                }else if(tag.equals("servlet-class")){
                    entity.setClz(value);
                    System.out.println(value);
                    System.out.println(entity.getName());
                    System.out.println(entity.getClz());
                }
            }else {
                if(tag.equals("servlet-name")){
                    mapping.setName(value);
                    System.out.println(value);
                }else if(tag.equals("url-pattern")){
                    mapping.addPattern(value);
                    System.out.println(value);
                }
            }
        }

    }

    public List<Entity> getEntities() {
        return entities;
    }

    public List<Mapping> getMappings() {
        return mappings;
    }
}