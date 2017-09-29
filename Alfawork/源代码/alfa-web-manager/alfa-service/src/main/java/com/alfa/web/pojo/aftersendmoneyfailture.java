package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * 红包发送失败
 */
public class aftersendmoneyfailture extends Entity implements Serializable {

    /**
     * openid
     */
    private String openid;

    /**
     * 红包活动ID
     */
    private Long activitiesid;


    /**
     * 订单ID
     */
    private Long orderid;

    /**
     * 订单号

     */
    private String orderno;


    /**
     * 错误信息
     */
    private String errormessage;


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

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }
}
