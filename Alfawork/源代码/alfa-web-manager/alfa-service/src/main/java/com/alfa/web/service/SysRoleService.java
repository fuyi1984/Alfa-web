package com.alfa.web.service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */
public interface SysRoleService {
    int insertRole(Role role);
    List<Role> findRole();
}
