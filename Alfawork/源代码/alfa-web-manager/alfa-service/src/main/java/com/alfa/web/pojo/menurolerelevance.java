package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * 菜单角色多对多关系表
 */
public class menurolerelevance extends Entity implements Serializable {

    /**
     * 菜单ID
     */
    private Long menuid;

    /**
     * 角色ID
     */
    private Long roleid;

    /**
     * 目录名
     */
    private String menuname;

    /**
     * 图标
     */
    private String icon;

    /**
     * Url
     */
    private String url;

    /**
     * 父节点
     */
    private String parentid;

    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getCascadeid() {
        return cascadeid;
    }

    public void setCascadeid(String cascadeid) {
        this.cascadeid = cascadeid;
    }

    /**
     * 节点
     */
    private String cascadeid;


    public Long getMenuid() {
        return menuid;
    }

    public void setMenuid(Long menuid) {
        this.menuid = menuid;
    }

    public Long getRoleid() {
        return roleid;
    }

    public void setRoleid(Long roleid) {
        this.roleid = roleid;
    }
}
