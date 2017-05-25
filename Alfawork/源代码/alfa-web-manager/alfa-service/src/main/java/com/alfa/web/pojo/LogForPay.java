package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/2.
 * 支付日志表
 */
public class LogForPay extends Entity implements Serializable {

    private Long PayId;

    public Long getPayId() {
        return PayId;
    }

    public void setPayId(Long payId) {
        PayId = payId;
    }

    /**
     * 支付金额
     */
    private String accountBalance;

    /**
     * 日志描述
     */
    private String description;

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LogForPay{" +
                "accountBalance='" + accountBalance + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
