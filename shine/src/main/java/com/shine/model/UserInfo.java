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

    @Setter
    @Getter
    private boolean locked;

    public UserInfo(Integer userId, String userTel, String userNick, String userPassword, String userMail,
        String userRole, boolean locked) {
        this.userId = userId;
        this.userTel = userTel;
        this.userNick = userNick;
        this.userPassword = userPassword;
        this.userMail = userMail;
        this.userRole = userRole;
        this.locked = locked;
    }

    public UserInfo() {
    }
}