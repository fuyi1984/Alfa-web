package com.alfa.web.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 * 用户表
 */
public class SysUsers extends Entity implements Serializable{

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 电话
     */
    private String phone;
    /**
     * 性别名称
     */
    private String sexname;
    /**
     * 性别类型
     */
    private String sex;
    /**
     * 用户状态名称
     */
    private String statusname;
    /**
     * 用户状态
     */
    private String status;
    /**
     * 用户类型名称
     */
    private String typesname;
    /**
     * 用户类型
     */
    private String types;
    /**
     * 地址
     */
    private String address;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 网页端Web服务身份验证码
     */
    private String token;
    /**
     * 手机端Web服务身份验证码
     */
    private String mobiletoken;
    /**
     * 密码输入错误次数
     */
    private int errorCount;
    /**
     * 手机端密码输入错误次数
     */
    private int errorCountformobile;
    /**
     * 登录IP地址
     */
    private String LoginIp;

    /**
     * 用户关联角色列表
     */
    private List<SysRole> roles;

    /**
     * 用户关联账户列表
     */
    private List<SysAccount> accountObj;


    public List<SysAccount> getAccountObj() {
        return accountObj;
    }

    public void setAccountObj(List<SysAccount> accountObj) {
        this.accountObj = accountObj;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public String getMobiletoken() {
        return mobiletoken;
    }

    public void setMobiletoken(String mobiletoken) {
        this.mobiletoken = mobiletoken;
    }

    public int getErrorCountformobile() {
        return errorCountformobile;
    }

    public void setErrorCountformobile(int errorCountformobile) {
        this.errorCountformobile = errorCountformobile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSexname() {
        return sexname;
    }

    public void setSexname(String sexname) {
        this.sexname = sexname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTypesname() {
        return typesname;
    }

    public void setTypesname(String typesname) {
        this.typesname = typesname;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(int errorCount) {
        this.errorCount = errorCount;
    }

    public String getLoginIp() {
        return LoginIp;
    }

    public void setLoginIp(String loginIp) {
        LoginIp = loginIp;
    }

    @Override
    public String toString() {
        return "SysUsers{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sexname='" + sexname + '\'' +
                ", sex='" + sex + '\'' +
                ", statusname='" + statusname + '\'' +
                ", status='" + status + '\'' +
                ", typesname='" + typesname + '\'' +
                ", types='" + types + '\'' +
                ", address='" + address + '\'' +
                ", remarks='" + remarks + '\'' +
                ", token='" + token + '\'' +
                ", mobiletoken='" + mobiletoken + '\'' +
                ", errorCount=" + errorCount +
                ", errorCountformobile=" + errorCountformobile +
                ", LoginIp='" + LoginIp + '\'' +
                '}';
    }
}
