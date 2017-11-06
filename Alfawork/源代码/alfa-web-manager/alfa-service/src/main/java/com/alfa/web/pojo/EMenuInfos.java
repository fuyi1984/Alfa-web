package com.alfa.web.pojo;

import java.io.Serializable;

import java.util.List;

/**
 * EasyUi目录
 * Created by Administrator on 2017/6/28.
 */
public class EMenuInfos extends Entity implements Serializable
{
    /**
     * 目录ID
     */
    private Long menuid;

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

    public String getParentmenuname() {
        return parentmenuname;
    }

    public void setParentmenuname(String parentmenuname) {
        this.parentmenuname = parentmenuname;
    }

    /**
     * 父目录名
     */

    private String parentmenuname;

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
}
