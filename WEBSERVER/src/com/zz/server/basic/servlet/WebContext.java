package com.zz.server.basic.servlet;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebContext {
    private List<Entity>entities;
    private List<Mapping> mappings;
    private Map<String,String> entityMap = new HashMap<>();;
    private Map<String,String> mappingMap = new HashMap<>();;
    public WebContext() {
    }

    public WebContext(List<Entity> entities, List<Mapping> mappings) {
        this.entities = entities;
        this.mappings = mappings;
        //servlet——name -> servlet-class
        for(Entity entity:entities){
            this.entityMap.put(entity.getName(),entity.getClz());
        }

        //url——pattern ——> servlet-name
        for(Mapping mapping:mappings){
            for(String string:mapping.getPattern()){
                mappingMap.put(string,mapping.getName());
            }
        }

    }


    public Map<String, String> getEntityMap() {
        return entityMap;
    }

    public Map<String, String> getMappingMap() {
        return mappingMap;
    }
}
