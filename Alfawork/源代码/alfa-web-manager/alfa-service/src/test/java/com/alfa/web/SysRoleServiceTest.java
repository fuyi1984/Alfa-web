package com.alfa.web;


import com.alfa.web.pojo.Role;
import com.alfa.web.service.Impl.SysRoleServiceImpl;
import com.alfa.web.service.SysRoleService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/24.
 */

public class SysRoleServiceTest extends TestBase{
    private static Logger logger = Logger.getLogger(SysRoleServiceTest.class);

    @Autowired
    private SysRoleService sysRoleService;

    @Test
    public void InsertRole(){
        Role role=new Role();
        role.setRole_name("管理员");
        role.setTypes(1);
        role.setStatus(1);
        role.setCreate_date(new Date());
        role.setBycreater_id(1L);
        Assert.assertEquals(1, sysRoleService.insertRole(role));
    }

    @Test
    public void findRole(){
        Assert.assertEquals(0,sysRoleService.findRole().size());
    }
}
