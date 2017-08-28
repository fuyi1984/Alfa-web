package com.alfa.web;


import com.alfa.web.pojo.SysRole;
import com.alfa.web.service.sys.SysRoleService;
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
        SysRole role = new SysRole();
        role.setRole_name("管理员");
        role.setTypes("1");
        role.setStatus("1");
        role.setCreatedDt(new Date());
        role.setCreatedBy("XXXX");
        role.setVersion(1L);
        role.setTypesname("111");
        role.setStatusname("222");
        Assert.assertEquals(1, sysRoleService.insert(role));
    }

}
