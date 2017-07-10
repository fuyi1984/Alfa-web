package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/29.
 * 收油地址历史记录表
 */
public class HistoryAddress extends Entity implements Serializable {

    private Long userid;

    private String address;

    private String iphone;

    /**
     * 省份
     */
    private String province;

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

    public String getFulladdress() {
        return fulladdress;
    }

    public void setFulladdress(String fulladdress) {
        this.fulladdress = fulladdress;
    }

    /**
     * 完整地址
     */
    private String fulladdress;

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

    public String getIphone() {
        return iphone;
    }

    public void setIphone(String iphone) {
        this.iphone = iphone;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
