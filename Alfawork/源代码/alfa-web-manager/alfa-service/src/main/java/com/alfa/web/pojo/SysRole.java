package com.alfa.web.pojo;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/26.
 * 角色表
 */
public class SysRole extends Entity implements Serializable {

    /**
     * 角色ID
     */
    private Long roleId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 角色名称
     */

    private String role_name;
    /**
     * 角色类型
     */
    private String types;
    /**
     * 角色类型名称
     */
    private String typesname;
    /**
     * 角色状态
     */
    private String status;
    /**
     * 角色状态名称
     */
    private String statusname;

    public String getMenuitem() {
        return menuitem;
    }

    public void setMenuitem(String menuitem) {
        this.menuitem = menuitem;
    }

    /**
     * 角色描述
     */
    private String roleDesc;

    @Override
    public String toString() {
        return "SysRole{" +
                "role_name='" + role_name + '\'' +
                ", types='" + types + '\'' +
                ", typesname='" + typesname + '\'' +
                ", status='" + status + '\'' +
                ", statusname='" + statusname + '\'' +
                ", roleDesc='" + roleDesc + '\'' +
                ", menuitem='" + menuitem + '\'' +
                '}';
    }

    /**
     * 菜单路径
     */
    private String menuitem;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getTypesname() {
        return typesname;
    }

    public void setTypesname(String typesname) {
        this.typesname = typesname;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusname() {
        return statusname;
    }

    public void setStatusname(String statusname) {
        this.statusname = statusname;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

}
