package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * 订单表
 */
public class Orders extends Entity implements Serializable {

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

    /**
     * 姓名
     */
    private String username;

    /**
     * 产废单位电话
     */
    private String iphone;

    /**
     * 地址
     */
    private String address;

    /**
     * 数量
     */
    private String num;

    /**
     * 单位名称
     */
    private String orgname;

    /**
     * 订单状态
     */
    private String orgstatus;

    /**
     * 用户Id
     */
    private Long workerid;

    /**
     * 收油人员电话
     */
    private String phone;

    /**
     * 收油人员真实姓名
     */
    private String realname;

    public Long getOrderid() {
        return orderid;
    }

    public void setOrderid(Long orderid) {
        this.orderid = orderid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getOrgstatus() {
        return orgstatus;
    }

    public void setOrgstatus(String orgstatus) {
        this.orgstatus = orgstatus;
    }

    public Long getWorkerid() {
        return workerid;
    }

    public void setWorkerid(Long workerid) {
        this.workerid = workerid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderid=" + orderid +
                ", username='" + username + '\'' +
                ", iphone='" + iphone + '\'' +
                ", address='" + address + '\'' +
                ", num='" + num + '\'' +
                ", orgname='" + orgname + '\'' +
                ", orgstatus='" + orgstatus + '\'' +
                ", workerid=" + workerid +
                ", phone='" + phone + '\'' +
                ", realname='" + realname + '\'' +
                '}';
    }
}
