package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/9/14.
 * 微信头像视图
 */
public class Vwweixinheadfile{

    private String openid;
    /**
     * 微信头像
     */
    private String headimgurl;

    /**
     * 是否已经下载到本地
     */
    private String isdownload;

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getIsdownload() {
        return isdownload;
    }

    public void setIsdownload(String isdownload) {
        this.isdownload = isdownload;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
