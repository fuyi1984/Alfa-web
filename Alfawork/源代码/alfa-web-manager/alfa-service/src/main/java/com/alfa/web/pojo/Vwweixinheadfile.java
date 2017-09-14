package com.alfa.web.pojo;

/**
 * Created by Administrator on 2017/9/14.
 * 微信头像视图
 */
public class Vwweixinheadfile {

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
}
