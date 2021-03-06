package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/17.
 * 评论回复表
 */
public class CommentReply extends Entity implements Serializable {

    /**
     * 评论回复内容
     */
    private String content;

    /**
     * 收运人员电话
     */
    private String mobile;

    /**
     * 产废单位电话
     */
    private String phone;

    /**
     * 评论ID
     */
    private Long commentid;

    /**
     * 收运人员的微信头像
     */
    private String mobileheadimgurl;

    /**
     * 产废单位的微信头像

     */
    private String phoneheadimgurl;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getCommentid() {
        return commentid;
    }

    public void setCommentid(Long commentid) {
        this.commentid = commentid;
    }
}
