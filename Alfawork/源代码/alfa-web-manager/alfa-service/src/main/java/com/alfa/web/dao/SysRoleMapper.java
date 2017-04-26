package com.alfa.web.dao;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
public interface SysRoleMapper {
    int insertRole(Role role);

    List<Role> findRole();
}
