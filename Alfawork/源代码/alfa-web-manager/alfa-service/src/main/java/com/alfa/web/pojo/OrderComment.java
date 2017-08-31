package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/6.
 * 订单评论
 */
public class OrderComment extends Entity implements Serializable {

    private String content;

    /**
     * 评论1
     */
    private String one;

    /**
     * 评论2
     */
    private String two;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 评论3

     */
    private String three;

    /**
     * 平均分
     */
    private String average;

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    /**
     * 自定义评论
     */
    private String four;

    /**
     * 收运人员电话
     */
    private String mobile;

    /**
     * 产废单位电话
     */
    private String phone;

    /**
     * 收运人员的微信头像
     */
    private String mobileheadimgurl;

    public String getMobileheadimgurl() {
        return mobileheadimgurl;
    }

    public void setMobileheadimgurl(String mobileheadimgurl) {
        this.mobileheadimgurl = mobileheadimgurl;
    }

    public String getPhoneheadimgurl() {
        return phoneheadimgurl;
    }

    public void setPhoneheadimgurl(String phoneheadimgurl) {
        this.phoneheadimgurl = phoneheadimgurl;
    }

    /**
     * 产废单位的微信头像

     */
    private String phoneheadimgurl;

    public String getOne() {
        return one;
    }

    public void setOne(String one) {
        this.one = one;
    }

    public String getTwo() {
        return two;
    }

    public void setTwo(String two) {
        this.two = two;
    }

    public String getThree() {
        return three;
    }

    public void setThree(String three) {
        this.three = three;
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private Long orderId;

    /**
     * 订单号

     */
    private String orderno;

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /*
     收运人员真实姓名
     */
    private String mobilerealname;

    /*
     产废人员真实姓名
     */
    private String phonerealname;

    public String getRealnum() {
        return realnum;
    }

    public void setRealnum(String realnum) {
        this.realnum = realnum;
    }

    /**
     * 实际油量

     */
    private String realnum;

    public String getMobilerealname() {
        return mobilerealname;
    }

    public void setMobilerealname(String mobilerealname) {
        this.mobilerealname = mobilerealname;
    }

    public String getPhonerealname() {
        return phonerealname;
    }

    public void setPhonerealname(String phonerealname) {
        this.phonerealname = phonerealname;
    }
}
