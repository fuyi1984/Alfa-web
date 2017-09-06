package com.alfa.web;


import com.alfa.web.pojo.EMenuInfos;
import com.alfa.web.pojo.SysRole;
import com.alfa.web.service.sys.EMenuInfosService;
import com.alfa.web.service.sys.SysRoleService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/24.
 */

public class SysRoleServiceTest extends TestBase{
    private static Logger logger = Logger.getLogger(SysRoleServiceTest.class);

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private EMenuInfosService eMenuInfosService;

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

    @Test
    public void InsertMenu(){

        EMenuInfos menu=new EMenuInfos();
        menu.setCascadeid("1");
        menu.setMenuname("中文");
        menu.setParentid("0");

        Criteria criteria = new Criteria();
        criteria.put("menuname", menu.getMenuname());
        //criteria.put("url", menu.getUrl());

        List<EMenuInfos> eMenuInfosList = this.eMenuInfosService.selectByParams(criteria);

        if(eMenuInfosList.size()==0){
            int result=this.eMenuInfosService.insertSelective(menu);
            if(result>0){
                //插入成功
                System.out.println("2");
            }else{
                //插入失败
                System.out.println("3");
            }
        }else{
            //数据已存在
            System.out.println("1");
        }
    }

}
