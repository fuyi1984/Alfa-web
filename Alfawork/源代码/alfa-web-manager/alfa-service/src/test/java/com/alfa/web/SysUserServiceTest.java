package com.alfa.web;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.SysRoleService;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
public class SysUserServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(SysUserServiceTest.class);

    @Autowired
    private SysUsersService sysUsersService;

    @Test
    public void InsertUser(){
        Criteria criteria = new Criteria();
        criteria.put("username", "1");
        criteria.put("roleId","1");
        List<SysUsers> UsersList = this.sysUsersService.selectByParams(criteria);
        Assert.assertEquals(UsersList.size(),1);
    }
}
