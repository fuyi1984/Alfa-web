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
