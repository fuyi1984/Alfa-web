package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/6/11.
 */
public class VerifyCode extends Entity implements Serializable {

    private Long verifySid;

    /**
     * 验证码
     */
    private String code;

    /**
     * 验证类型（0手机注册，1邮箱注册，2企业注册）
     */
    private Integer type;

    /**
     * 绑定帐号（手机/邮箱/账户）
     */
    private String boundAccount;

    public Long getVerifySid() {
        return verifySid;
    }

    public void setVerifySid(Long verifySid) {
        this.verifySid = verifySid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getBoundAccount() {
        return boundAccount;
    }

    public void setBoundAccount(String boundAccount) {
        this.boundAccount = boundAccount;
    }
}
