package com.shine.basic.rep;

import java.io.Serializable;

import com.shine.basic.BaseRequest;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户查询
 *
 * @author wb-cb236432
 * @create 2018-08-06 15:20
 **/
public class UserQuery extends BaseRequest implements Serializable {
    private static final long serialVersionUID = -8817777504282488627L;

    @Setter
    @Getter
    private Integer userId;

    @Setter
    @Getter
    private String userTel;

    @Setter
    @Getter
    private String userNick;

    @Setter
    @Getter
    private String userPassword;

    @Setter
    @Getter
    private String userMail;

    @Setter
    @Getter
    private String userRole;
}
