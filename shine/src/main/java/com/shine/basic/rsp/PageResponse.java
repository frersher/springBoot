package com.shine.basic.rsp;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * PageResponse
 * @author wb-cb236432
 * @create 2018-08-06 16:37
 **/
public class PageResponse<T> extends Response<T> implements Serializable{
    private static final long serialVersionUID = 3510750627426564565L;

    /**
     * 当前页
     */
    @Getter
    @Setter
    private Integer pageNum;

    /**
     * 总记录数
     */
    @Getter
    @Setter
    private Long count;

    /**
     * 总页数
     */
    @Getter
    @Setter
    private Integer pages;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
