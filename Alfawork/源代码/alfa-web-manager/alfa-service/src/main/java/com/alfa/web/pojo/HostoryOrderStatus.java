package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * 订单完成历史记录表
 */
public class HostoryOrderStatus extends Entity implements Serializable {

    private String phone;

    /**
     * 实际收油量
     */
    private String realnum;

    /**
     * 订单ID
     */
    private Long orderid;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealnum() {
        return realnum;
    }

    public void setRealnum(String realnum) {
        this.realnum = realnum;
    }

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getRealtotal() {
        return realtotal;
    }

    public void setRealtotal(String realtotal) {
        this.realtotal = realtotal;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 预计收油量的统计
     */
    private String total;

    /**
     * 实际收油的统计
     */
    private String realtotal;

    /**
     * 申报人电话
     */
    private String iphone;

    /**
     * 申报人姓名
     */
    private String username;
}
