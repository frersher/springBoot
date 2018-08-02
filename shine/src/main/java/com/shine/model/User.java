package com.shine.model;

/**
 * 用户实体
 *
 * @author wb-cb236432
 * @create 2018-08-02 15:14
 **/
public class User {
    private String userNick;
    private String emailAdress;
    private String password;

    public String getUserNick() {
        return userNick;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
