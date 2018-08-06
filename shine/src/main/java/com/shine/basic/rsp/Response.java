package com.shine.basic.rsp;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author bang.chen
 * @since 4/28/18
 */
public class Response<T> implements Serializable {

    private static final long serialVersionUID = 3199844267807512140L;
    @Getter
    @Setter
    T data;

    @Getter
    @Setter
    boolean succeeded;

    @Getter
    @Setter
    String responseCode;

    @Getter
    @Setter
    String responseMsg;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}