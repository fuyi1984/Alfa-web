package com.alfa.web.vo;

import java.util.List;

public class treedata {

    private Long parentId;

    private Long id;

    private String text;

    private List<treedata> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<treedata> getChildren() {
        return children;
    }

    public void setChildren(List<treedata> children) {
        this.children = children;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
