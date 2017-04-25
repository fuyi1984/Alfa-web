package com.alfa.web.dao;

import com.alfa.web.pojo.Role;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
public interface SysRoleMapper {
    int insertRole(Role role);

    List<Role> findRole();
}
