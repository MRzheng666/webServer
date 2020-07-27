package com.zz.server.basic.servlet;

import java.util.HashSet;
import java.util.Set;

//请求地址的类
public class Mapping {
    private String name;
    private Set<String> patterns;

    public Mapping() {
        patterns = new HashSet<>();
    }

    public Mapping(String name, Set<String> pattern) {
        this.name = name;
        this.patterns = pattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getPattern() {
        return patterns;
    }

    public void setPattern(Set<String> pattern) {
        this.patterns = pattern;
    }
    public void addPattern(String pattern){
        patterns.add(pattern);
    }
}
