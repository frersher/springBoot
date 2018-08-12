package com.shine.basic;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;

public abstract class BaseRequest implements Serializable {

    public static final Integer DEFAULT_PAGE_NUM = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 2;
    private static final long serialVersionUID = -8819751388220084356L;
    private Integer pageNum;

    private Integer pageSize;

    public Integer getPageNum() {
        if (null != pageNum) {
            return pageNum;
        } else {
            return DEFAULT_PAGE_NUM;
        }
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        if (null != pageSize) {
            return pageSize;
        } else {
            return DEFAULT_PAGE_SIZE;
        }
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}