package com.alfa.web.vo;

import java.util.Date;
import java.util.List;

public class Menus {

    /**
     * 目录ID
     */
    private Long MenuId;

    /**
     * 父节点
     */
    private Long parentId;

    /**
     * 目录名
     */
    private String MenuName;

    /**
     * 图标
     */
    private String Icon;

    /**
     * Url
     */
    private String Url;

    /**
     * 创建时间
     */
    private Date CreateTime;

    /**
     * 子节点
     */
    private List<Menus> MenuInfos;

    public Long getMenuId() {
        return MenuId;
    }

    public void setMenuId(Long menuId) {
        MenuId = menuId;
    }

    public String getMenuName() {
        return MenuName;
    }

    public void setMenuName(String menuName) {
        MenuName = menuName;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public List<Menus> getMenuInfos() {
        return MenuInfos;
    }

    public void setMenuInfos(List<Menus> menuInfos) {
        MenuInfos = menuInfos;
    }
}
