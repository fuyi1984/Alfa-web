package com.alfa.web.pojo;

import com.alfa.web.util.jackson.CustomJsonDateDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/9/18.
 * 红包活动表
 */
public class moneyactivities extends Entity implements Serializable {

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 红包金额
     */
    private String money;

    /**
     * 红包总数
     */
    private String totalnum;


    /**
     * 已发红包数
     */
    private String sendednum;

    /**
     * 剩余红包数
     */
    private String remainingnum;

    /**
     * 活动开始时间
     */
    private Date starttime;


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 活动结束时间

     */
    private Date endtime;

    /**
     * 活动状态
     */
    private String status;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTotalnum() {
        return totalnum;
    }

    public void setTotalnum(String totalnum) {
        this.totalnum = totalnum;
    }

    public String getSendednum() {
        return sendednum;
    }

    public void setSendednum(String sendednum) {
        this.sendednum = sendednum;
    }

    public String getRemainingnum() {
        return remainingnum;
    }

    public void setRemainingnum(String remainingnum) {
        this.remainingnum = remainingnum;
    }

    public Date getStarttime() {
        return starttime;
    }

    //@JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    //@JsonDeserialize(using = CustomJsonDateDeserializer.class)
    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    /*
     单笔红包最大金额
     */
    private String maxprice;

    /*
     单笔红包最小金额
     */
    private String minprice;

    public String getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(String maxprice) {
        this.maxprice = maxprice;
    }

    public String getMinprice() {
        return minprice;
    }

    public void setMinprice(String minprice) {
        this.minprice = minprice;
    }

    /**
     * 是否显示
     */
    private String isvisible;

    public String getIsvisible() {
        return isvisible;
    }

    public void setIsvisible(String isvisible) {
        this.isvisible = isvisible;
    }
}
