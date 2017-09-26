package com.alfa.web.vo;

/**
 * Created by Administrator on 2017/6/11.
 */
public class RegisterUser {

    private String mobile;
    private String captcha;
    private String openid;
    private String logintype;

    public String getLogintype() {
        return logintype;
    }

    public void setLogintype(String logintype) {
        this.logintype = logintype;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
