package com.alfa.web.vo;

/**
 * 红包支付
 */
public class moneyvo {

    /**
     * openid
     */
    private String openid;

    /**
     * 金额
     */
    private String num;

    /**
     * 商户号
     */
    private String key;


    /**
     * 密钥
     */
    private String paykey;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPaykey() {
        return paykey;
    }

    public void setPaykey(String paykey) {
        this.paykey = paykey;
    }
}
