package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/30.
 * 微信OpenId表
 */
public class td_weixin_users extends Entity implements Serializable {

    private String openid;

    private String headimgurl;

    @Override
    public String toString() {
        return "td_weixin_users{" +
                "openid='" + openid + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", state='" + state + '\'' +
                ", userid=" + userid +
                '}';
    }

    private String state;

    private Long userid;

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
}
