package com.alfa.web.util.pojo;

import com.alfa.web.pojo.SysAccount;
import com.alfa.web.pojo.SysRole;
import com.alfa.web.pojo.SysUsers;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/27.
 */
public class UserSession implements Serializable {
    private String id;
    private Long creationTime;
    private SysUsers user;
    private SysAccount account;
    private SysRole role;
    private String languageCode;

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Long creationTime) {
        this.creationTime = creationTime;
    }

    public SysUsers getUser() {
        return user;
    }

    public void setUser(SysUsers user) {
        this.user = user;
    }

    public SysAccount getAccount() {
        return account;
    }

    public void setAccount(SysAccount account) {
        this.account = account;
    }

    public SysRole getRole() {
        return role;
    }

    public void setRole(SysRole role) {
        this.role = role;
    }
}
