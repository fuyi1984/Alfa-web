package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/18.
 * 红包活动关注表
 */
public class moneyactivitiesconcern extends Entity implements Serializable {

    private String openid;

    /**
     * 活动ID
     */
    private String activitiesid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getActivitiesid() {
        return activitiesid;
    }

    public void setActivitiesid(String activitiesid) {
        this.activitiesid = activitiesid;
    }
}

