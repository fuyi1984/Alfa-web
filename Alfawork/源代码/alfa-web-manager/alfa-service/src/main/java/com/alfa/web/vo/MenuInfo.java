package com.alfa.web.vo;

import java.util.Date;

public class MenuInfo {

    private Long MenuId;

    private String MenuNames;

    private String Icon;

    private String Url;

    private Date CreateTime;

    public Long getMenuId() {
        return MenuId;
    }

    public void setMenuId(Long menuId) {
        MenuId = menuId;
    }

    public String getMenuNames() {
        return MenuNames;
    }

    public void setMenuNames(String menuNames) {
        MenuNames = menuNames;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }
}
