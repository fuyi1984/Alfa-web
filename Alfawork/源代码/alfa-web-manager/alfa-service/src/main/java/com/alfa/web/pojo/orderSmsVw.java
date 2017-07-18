package com.alfa.web.pojo;

import java.util.Date;

/**
 * 提交订单后短信通知管理员分配任务
 */
public class orderSmsVw{

    private Long orderid;

    /**
     * 订单号

     */
    private String orderno;

    /**
     * 产废单位电话
     */
    private String iphone;

    /**
     * 单位名称
     */
    private String orgname;

    /**
     * 确认时间
     */
    private Date confirmDt;

    public Date getConfirmDt() {
        return confirmDt;
    }

    public void setConfirmDt(Date confirmDt) {
        this.confirmDt = confirmDt;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private String orgstatus;

    private String isSms;

    private Date createdDt;

    private Date updatedDt;

    /**
     * 收油人员电话
     */
    private String phone;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getOrgstatus() {
        return orgstatus;
    }

    public void setOrgstatus(String orgstatus) {
        this.orgstatus = orgstatus;
    }

    public String getIsSms() {
        return isSms;
    }

    public void setIsSms(String isSms) {
        this.isSms = isSms;
    }

    public Date getCreatedDt() {
        return createdDt;
    }

    public void setCreatedDt(Date createdDt) {
        this.createdDt = createdDt;
    }

    public Date getUpdatedDt() {
        return updatedDt;
    }

    public void setUpdatedDt(Date updatedDt) {
        this.updatedDt = updatedDt;
    }

}
