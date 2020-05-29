package com.shine.basic;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SysLog implements Serializable {
    private Long id;

    //用户名
    private String username;

    //操作
    private String operation;

    //方法名
    private String method;

    //参数
    private String params;


    //操作时间
    private Date createDate;

}