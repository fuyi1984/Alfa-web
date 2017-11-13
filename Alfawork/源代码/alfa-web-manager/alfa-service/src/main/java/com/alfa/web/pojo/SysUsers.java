package com.alfa.web.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/25.
 * 用户表
 */
public class SysUsers extends Entity implements Serializable{

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 用户Id
     */

    private Long userId;

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password64;
    /**
     * 密码
     */
    private String password;

    public String getPassword64() {
        return password64;
    }

    public void setPassword64(String password64) {
        this.password64 = password64;
    }

    /**
     * 纬度
     */
    private String latitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * 经度
     */
    private String longitude;

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

    public String getOrgname() {
        return orgname;
    }

    public void setOrgname(String orgname) {
        this.orgname = orgname;
    }

    /**
     * 单位名称

     */
    private String orgname;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 网页端Web服务身份验证码
     */
    private String token;
    /**
     * 微信公众号Web服务身份验证码
     */
    private String mobiletoken;

    public String getWxtoken() {
        return wxtoken;
    }

    public void setWxtoken(String wxtoken) {
        this.wxtoken = wxtoken;
    }

    public String getApptoken() {
        return apptoken;
    }

    public void setApptoken(String apptoken) {
        this.apptoken = apptoken;
    }

    /**
     * 微信小程序Web服务身份验证码
     */

    private String wxtoken;

    /**
     * 手机APPWeb服务身份验证码
     */
    private String apptoken;

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
     * 手机验证码
     */
    private String VerifyCode;

    /**
     * 验证码
     */
    private String captcha;

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getVerifyCode() {
        return VerifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        VerifyCode = verifyCode;
    }

    /**
     * 角色ID
     */
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 真实姓名
     */
    private String realname;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    /**
     * 角色名称
     */

    private String role_name;

    /**
     * 菜单路径
     */
    private String menuitem;

    public String getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(String menuitem) {
        this.menuitem = menuitem;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    /**
     * 用户关联角色列表
     */
    private List<SysRole> roles;

    @Override
    public String toString() {
        return "SysUsers{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password64='" + password64 + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", sexname='" + sexname + '\'' +
                ", sex='" + sex + '\'' +
                ", statusname='" + statusname + '\'' +
                ", status='" + status + '\'' +
                ", typesname='" + typesname + '\'' +
                ", types='" + types + '\'' +
                ", address='" + address + '\'' +
                ", orgname='" + orgname + '\'' +
                ", remarks='" + remarks + '\'' +
                ", token='" + token + '\'' +
                ", mobiletoken='" + mobiletoken + '\'' +
                ", errorCount=" + errorCount +
                ", errorCountformobile=" + errorCountformobile +
                ", LoginIp='" + LoginIp + '\'' +
                ", VerifyCode='" + VerifyCode + '\'' +
                ", captcha='" + captcha + '\'' +
                ", roleId=" + roleId +
                ", realname='" + realname + '\'' +
                ", role_name='" + role_name + '\'' +
                ", menuitem='" + menuitem + '\'' +
                ", roles=" + roles +
                ", accountObj=" + accountObj +
                ", weixinid=" + weixinid +
                ", openid='" + openid + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", state='" + state + '\'' +
                '}';
    }

    /**
     * 用户关联账户列表
     */
    private List<SysAccount> accountObj;

    private Long weixinid;

    private String openid;

    private String headimgurl;

    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(Long weixinid) {
        this.weixinid = weixinid;
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

    /**
     * 文件ID
     */
    private Long fileid;

    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }



    public Long getFileids() {
        return fileids;
    }

    public void setFileids(Long fileids) {
        this.fileids = fileids;
    }

    public String getRegurl() {
        return regurl;
    }

    public void setRegurl(String regurl) {
        this.regurl = regurl;
    }

    public String getRegtype() {
        return regtype;
    }

    public void setRegtype(String regtype) {
        this.regtype = regtype;
    }


    /**
     * 文件ID
     */
    private Long fileids;

    /**
     * 文件链接
     */
    private String regurl;

    /**
     * 文件类型

     */
    private String regtype;
}
