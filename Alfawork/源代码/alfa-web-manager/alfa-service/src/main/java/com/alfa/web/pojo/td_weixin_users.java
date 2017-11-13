package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/30.
 * 微信OpenId表
 */
public class td_weixin_users extends Entity implements Serializable {

    private String openid;

    private String headimgurl;

    private String state;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 微信昵称
     */
    private String nickname;

    private Long userid;

    private String mobile;

    private String mobiletoken;

    private String wxtoken;

    @Override
    public String toString() {
        return "td_weixin_users{" +
                "openid='" + openid + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", state='" + state + '\'' +
                ", nickname='" + nickname + '\'' +
                ", userid=" + userid +
                ", mobile='" + mobile + '\'' +
                ", mobiletoken='" + mobiletoken + '\'' +
                ", wxtoken='" + wxtoken + '\'' +
                ", realname='" + realname + '\'' +
                ", sysuserid=" + sysuserid +
                ", roleId=" + roleId +
                ", role_name='" + role_name + '\'' +
                ", orgname='" + orgname + '\'' +
                ", address='" + address + '\'' +
                ", totalrealnum='" + totalrealnum + '\'' +
                ", totalaverage='" + totalaverage + '\'' +
                ", realnum='" + realnum + '\'' +
                ", average='" + average + '\'' +
                ", commentnum='" + commentnum + '\'' +
                ", nototalaverage='" + nototalaverage + '\'' +
                ", isdownload='" + isdownload + '\'' +
                ", localurl='" + localurl + '\'' +
                '}';
    }

    public String getWxtoken() {
        return wxtoken;
    }

    public void setWxtoken(String wxtoken) {
        this.wxtoken = wxtoken;
    }

    private String realname;

    private Long sysuserid;

    private Long roleId;

    /**
     * 角色名
     */
    private String role_name;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getSysuserid() {
        return sysuserid;
    }

    public void setSysuserid(Long sysuserid) {
        this.sysuserid = sysuserid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String orgname;

    private String address;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobiletoken() {
        return mobiletoken;
    }

    public void setMobiletoken(String mobiletoken) {
        this.mobiletoken = mobiletoken;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String totalrealnum;

    private String totalaverage;

    private String realnum;

    private String average;

    public String getTotalrealnum() {
        return totalrealnum;
    }

    public void setTotalrealnum(String totalrealnum) {
        this.totalrealnum = totalrealnum;
    }

    public String getTotalaverage() {
        return totalaverage;
    }

    public void setTotalaverage(String totalaverage) {
        this.totalaverage = totalaverage;
    }

    public String getRealnum() {
        return realnum;
    }

    public void setRealnum(String realnum) {
        this.realnum = realnum;
    }

    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    /**
     * 评论数
     */
    private String commentnum;

    public String getCommentnum() {
        return commentnum;
    }

    public void setCommentnum(String commentnum) {
        this.commentnum = commentnum;
    }

    /**
     * 总的评论平均分除以评论数
     */
    private String nototalaverage;

    public String getNototalaverage() {
        return nototalaverage;
    }

    public void setNototalaverage(String nototalaverage) {
        this.nototalaverage = nototalaverage;
    }

    /**
     * 是否已下载
     */
    private String isdownload;


    /**
     * 本地Url路径
     */
    private String localurl;

    public String getIsdownload() {
        return isdownload;
    }

    public void setIsdownload(String isdownload) {
        this.isdownload = isdownload;
    }

    public String getLocalurl() {
        return localurl;
    }

    public void setLocalurl(String localurl) {
        this.localurl = localurl;
    }
}
