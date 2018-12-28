package com.example.administrator.activitymanagement.domain;

import java.io.Serializable;

public class ActivityListBean implements Serializable {
    private String aid;
    private String aName;
    private String aimageId;
    private String aUid;
    private String aUsername;
    private String aOpenTime;
    private String aEndTime;
    private String aPlace;
    private String aInfo;
    private String aTelephone;

    public ActivityListBean() {
    }

    public ActivityListBean(String aid, String aName, String aimageId, String aUid, String aUsername, String aOpenTime, String aEndTime, String aPlace, String aInfo, String aTelephone) {
        this.aid = aid;
        this.aName = aName;
        this.aimageId = aimageId;
        this.aUid = aUid;
        this.aUsername = aUsername;
        this.aOpenTime = aOpenTime;
        this.aEndTime = aEndTime;
        this.aPlace = aPlace;
        this.aInfo = aInfo;
        this.aTelephone = aTelephone;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getaName() {
        return aName;
    }

    public void setaName(String aName) {
        this.aName = aName;
    }

    public String getAimageId() {
        return aimageId;
    }

    public void setAimageId(String aimageId) {
        this.aimageId = aimageId;
    }

    public String getaUid() {
        return aUid;
    }

    public void setaUid(String aUid) {
        this.aUid = aUid;
    }

    public String getaUsername() {
        return aUsername;
    }

    public void setaUsername(String aUsername) {
        this.aUsername = aUsername;
    }

    public String getaOpenTime() {
        return aOpenTime;
    }

    public void setaOpenTime(String aOpenTime) {
        this.aOpenTime = aOpenTime;
    }

    public String getaEndTime() {
        return aEndTime;
    }

    public void setaEndTime(String aEndTime) {
        this.aEndTime = aEndTime;
    }

    public String getaPlace() {
        return aPlace;
    }

    public void setaPlace(String aPlace) {
        this.aPlace = aPlace;
    }

    public String getaInfo() {
        return aInfo;
    }

    public void setaInfo(String aInfo) {
        this.aInfo = aInfo;
    }

    public String getaTelephone() {
        return aTelephone;
    }

    public void setaTelephone(String aTelephone) {
        this.aTelephone = aTelephone;
    }

    @Override
    public String toString() {
        return "ActivityListBean{" +
                "aid='" + aid + '\'' +
                ", aName='" + aName + '\'' +
                ", aimageId='" + aimageId + '\'' +
                ", aUid='" + aUid + '\'' +
                ", aUsername='" + aUsername + '\'' +
                ", aOpenTime='" + aOpenTime + '\'' +
                ", aEndTime='" + aEndTime + '\'' +
                ", aPlace='" + aPlace + '\'' +
                ", aInfo='" + aInfo + '\'' +
                ", aTelephone='" + aTelephone + '\'' +
                '}';
    }
}
