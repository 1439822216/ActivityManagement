package com.example.administrator.activitymanagement.domain;

import java.io.Serializable;

public class UserInfo implements Serializable {
    private String uid;
    private String name;
    private String telephone;
    private String clazz;
    private String touxiang;
    private String username;
    private String password;

    public UserInfo() {
    }

    public UserInfo(String uid, String name, String telephone, String clazz, String touxiang, String username, String password) {
        this.uid = uid;
        this.name = name;
        this.telephone = telephone;
        this.clazz = clazz;
        this.touxiang = touxiang;
        this.username = username;
        this.password = password;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getTouxiang() {
        return touxiang;
    }

    public void setTouxiang(String touxiang) {
        this.touxiang = touxiang;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
