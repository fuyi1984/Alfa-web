package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * EasyUi目录
 * Created by Administrator on 2017/6/28.
 */
public class EMenuInfos extends Entity implements Serializable
{
    /**
     * 目录ID
     */
    private Long MenuId;

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
     * 父节点
     */
    private Long ParentId;

    /**
     * 节点
     */
    private Long CascadeId;

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

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Long getParentId() {
        return ParentId;
    }

    public void setParentId(Long parentId) {
        ParentId = parentId;
    }

    public Long getCascadeId() {
        return CascadeId;
    }

    public void setCascadeId(Long cascadeId) {
        CascadeId = cascadeId;
    }
}
