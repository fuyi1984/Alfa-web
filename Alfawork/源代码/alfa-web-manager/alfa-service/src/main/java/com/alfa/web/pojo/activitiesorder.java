package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * 红包活动订单表
 */
public class activitiesorder extends Entity implements Serializable {

    /**
     * openid
     */
    private String openid;

    /**
     * 红包活动ID
     */
    private Long activitiesid;

    /**
     * 活动标题
     */
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 订单ID
     */
    private Long orderid;

    /**
     * 是否关注
     */
    private String isfollow;

    /**
     * 是否提交
     */
    private String issubmit;

    /**
     * 是否完成
     */
    private String isfinish;

    /**
     * 是否显示
     */
    private String visible;

    public String getVisible() {
        return visible;
    }

    public void setVisible(String visible) {
        this.visible = visible;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    /**
     * 订单号

     */
    private String orderno;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Long getActivitiesid() {
        return activitiesid;
    }

    public void setActivitiesid(Long activitiesid) {
        this.activitiesid = activitiesid;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getIsfollow() {
        return isfollow;
    }

    public void setIsfollow(String isfollow) {
        this.isfollow = isfollow;
    }

    public String getIssubmit() {
        return issubmit;
    }

    public void setIssubmit(String issubmit) {
        this.issubmit = issubmit;
    }

    public String getIsfinish() {
        return isfinish;
    }

    public void setIsfinish(String isfinish) {
        this.isfinish = isfinish;
    }

    /**
     * 状态
     */
    private String status;

    public String getaStatus() {
        return aStatus;
    }

    public void setaStatus(String aStatus) {
        this.aStatus = aStatus;
    }

    /**
     * 活动状态

     */
    private String aStatus;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 手机号
     */
    public String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
