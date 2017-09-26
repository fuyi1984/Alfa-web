package com.alfa.web.vo;

import com.alfa.web.pojo.SysRole;
import com.alfa.web.pojo.SysUsers;

/**
 * Created by Administrator on 2017/6/11.
 */
public class MobileUser {
    private SysUsers user;
    private SysRole role;

    public SysUsers getUser() {
        return user;
    }

    public void setUser(SysUsers user) {
        this.user = user;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }
}
