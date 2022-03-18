package com.shine.util;

import java.io.Serializable;
import java.util.List;

public class BaseTreeVO implements Serializable {
    private static final long serialVersionUID = 6753923840108631912L;
    private List children;
    private Long parentId;
    private Long id;

    public BaseTreeVO() {
    }

    public Long getParentId() {
        return this.parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public <T extends BaseTreeVO> List<T> getChildren() {
        return this.children;
    }

    public <T extends BaseTreeVO> void setChildren(List<T> children) {
        this.children = children;
    }

    public String toString() {
        return super.toString();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}