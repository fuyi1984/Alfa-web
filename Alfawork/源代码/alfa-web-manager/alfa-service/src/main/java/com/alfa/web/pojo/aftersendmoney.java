package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * 红包发送成功
 */
public class aftersendmoney extends Entity implements Serializable {

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

    /**
     * 金额
     */
    private String money;

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
