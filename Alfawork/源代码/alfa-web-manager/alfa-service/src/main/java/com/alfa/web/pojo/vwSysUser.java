package com.alfa.web.pojo;

/**
 * Created by Administrator on 2017/7/18.
 * 用户视图
 */
public class vwSysUser {

    private Long userId;

    private String phone;

    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
