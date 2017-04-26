package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/26.
 * 账户表
 */
public class SysAccount extends Entity implements Serializable {
    /**
     * 账户名
     */
    private String Account_name;
    /**
     * 账户类型
     */
    private String Account_type;
    /**
     * 账户类型名称
     */
    private String Account_typename;
    /**
     * 用户积分
     */
    private Long UsableCredit;
    /**
     * 用户余额
     */
    private String accountBalance;
    /**
     * 用户备用金
     */
    private String accountdeposit;
    /**
     * 状态
     */
    private String status;
    /**
     * 状态名
     */
    private String statusName;
    /**
     * 描述
     */
    private String description;
    /**
     * 用户ID
     */
    private Long userId;

    public String getAccount_name() {
        return Account_name;
    }

    public void setAccount_name(String account_name) {
        Account_name = account_name;
    }

    public String getAccount_type() {
        return Account_type;
    }

    public void setAccount_type(String account_type) {
        Account_type = account_type;
    }

    public String getAccount_typename() {
        return Account_typename;
    }

    public void setAccount_typename(String account_typename) {
        Account_typename = account_typename;
    }

    public Long getUsableCredit() {
        return UsableCredit;
    }

    public void setUsableCredit(Long usableCredit) {
        UsableCredit = usableCredit;
    }

    public String getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(String accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountdeposit() {
        return accountdeposit;
    }

    public void setAccountdeposit(String accountdeposit) {
        this.accountdeposit = accountdeposit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "SysAccount{" +
                "Account_name='" + Account_name + '\'' +
                ", Account_type='" + Account_type + '\'' +
                ", Account_typename='" + Account_typename + '\'' +
                ", UsableCredit=" + UsableCredit +
                ", accountBalance='" + accountBalance + '\'' +
                ", accountdeposit='" + accountdeposit + '\'' +
                ", status='" + status + '\'' +
                ", statusName='" + statusName + '\'' +
                ", description='" + description + '\'' +
                ", userId=" + userId +
                '}';
    }
}
