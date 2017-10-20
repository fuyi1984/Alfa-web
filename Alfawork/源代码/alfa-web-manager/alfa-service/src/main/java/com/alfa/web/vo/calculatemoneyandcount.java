package com.alfa.web.vo;

/**
 * 统计用户发送的红包总数和红包总金额
 */
public class calculatemoneyandcount {

    /**
     * openid
     */
    private String openid;

    /**
     * 红包总数
     */
    private String totalcount;

    /**
     * 红包总金额
     */
    private String totalmoney;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public String getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(String totalmoney) {
        this.totalmoney = totalmoney;
    }
}
