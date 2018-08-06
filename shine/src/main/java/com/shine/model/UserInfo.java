package com.shine.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public class UserInfo {
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