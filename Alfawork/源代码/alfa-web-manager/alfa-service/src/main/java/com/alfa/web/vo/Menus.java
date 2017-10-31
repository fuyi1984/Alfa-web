package com.alfa.web.vo;

import java.util.ArrayList;
import java.util.List;

public class Menus {

    public Menus(){
        MenuInfos=new ArrayList<MenuInfo>();
    }

    private Long MenuId;

    private String MenuName;

    private String Icon;

    private List<MenuInfo> MenuInfos;

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

    public List<MenuInfo> getMenuInfos() {
        return MenuInfos;
    }

    public void setMenuInfos(List<MenuInfo> menuInfos) {
        MenuInfos = menuInfos;
    }
}
