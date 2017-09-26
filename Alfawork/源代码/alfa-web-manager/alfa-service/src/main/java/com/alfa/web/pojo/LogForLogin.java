package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/5/2.
 * 用户登录日志
 */
public class LogForLogin extends Entity implements Serializable {

    /***
     * 用户名
     */
    private String username;
    /***
     * 日志描述
     */
    private String description;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LogForLogin{" +
                "username='" + username + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
