package com.alfa.web.pojo;

import java.io.Serializable;
import java.util.Date;

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
     * 实收数量
     */
    private String realnum;

    @Override
    public String toString() {
        return "Orders{" +
                "orderid=" + orderid +
                ", orderno='" + orderno + '\'' +
                ", realnum='" + realnum + '\'' +
                ", remark='" + remark + '\'' +
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 备注

     */
    private String remark;

    /**
     * 产废单位用户姓名
     */
    private String username;

    /**
     * 产废单位电话
     */
    private String iphone;

    public String getRealnum() {
        return realnum;
    }

    public void setRealnum(String realnum) {
        this.realnum = realnum;
    }

    /**
     * 收油地址

     */
    private String address;

    /**
     * 收油地址ID
     */
    private Long addressId;

    /**
     * 省份
     */
    private String province;

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTownandstreets() {
        return townandstreets;
    }

    public void setTownandstreets(String townandstreets) {
        this.townandstreets = townandstreets;
    }

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 城镇街道
     */
    private String townandstreets;

    /**
     * 预收数量
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
     * 收运人员用户Id
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


    /**
     * 确认时间
     */
    private Date confirmDt;

    /**
     * 是否已经发送订单分配通知短信
     */
    private String isSms;

    public String getIsSms() {
        return isSms;
    }

    public void setIsSms(String isSms) {
        this.isSms = isSms;
    }

    public Date getConfirmDt() {
        return confirmDt;
    }

    public void setConfirmDt(Date confirmDt) {
        this.confirmDt = confirmDt;
    }

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

}
