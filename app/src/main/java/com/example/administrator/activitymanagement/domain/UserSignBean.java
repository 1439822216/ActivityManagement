package com.example.administrator.activitymanagement.domain;

public class UserSignBean {
    private String uid;
    private String aid;

    public UserSignBean() {
    }

    public UserSignBean(String uid, String aid) {
        this.uid = uid;
        this.aid = aid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    @Override
    public String toString() {
        return "UserSignBean{" +
                "uid='" + uid + '\'' +
                ", aid='" + aid + '\'' +
                '}';
    }
}
