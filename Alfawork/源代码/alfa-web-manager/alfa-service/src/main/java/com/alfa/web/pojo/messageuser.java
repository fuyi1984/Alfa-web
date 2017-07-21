package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/20.
 * 消息用户表
 */
public class messageuser extends Entity implements Serializable {

    private Long userid;

    private Long messageid;

    private String isread;

    private String phone;

    private String realname;

    private String title;

    private String content;

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

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public Long getMessageid() {
        return messageid;
    }

    public void setMessageid(Long messageid) {
        this.messageid = messageid;
    }

    public String getIsread() {
        return isread;
    }

    public void setIsread(String isread) {
        this.isread = isread;
    }
}
