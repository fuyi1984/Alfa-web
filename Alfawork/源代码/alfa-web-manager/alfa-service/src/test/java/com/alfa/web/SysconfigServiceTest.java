package com.alfa.web;

import com.alfa.web.pojo.SysConfig;
import com.alfa.web.service.sys.SysconfigService;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/26.
 */
public class SysconfigServiceTest extends TestBase {
    private static Logger logger = Logger.getLogger(SysconfigServiceTest.class);

    @Autowired
    private SysconfigService sysConfigService;

    @Test
    public void InsertSysconfig() {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigKey("1");
        sysConfig.setConfigName("1");
        sysConfig.setConfigValue("1");
        sysConfig.setDescription("1");
        sysConfig.setCreatedBy("1");
        sysConfig.setCreatedDt(new Date());
        sysConfig.setUpdatedBy("2");
        sysConfig.setUpdatedDt(new Date());
        sysConfig.setVersion(1L);

        Assert.assertEquals(1, sysConfigService.insertSysConfig(sysConfig));
    }

    @Test
    public void insertSelective() {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setConfigKey("mail.smtp.host");
        sysConfig.setConfigName("mail");
        sysConfig.setConfigValue("smtp.exmail.qq.com");
        sysConfig.setDescription("smtp");
        sysConfig.setCreatedBy("fuyi");
        sysConfig.setCreatedDt(new Date());
        Assert.assertEquals(1, sysConfigService.insertSelective(sysConfig));
    }

    @Test
    public void selectByParams(){

        Criteria criteria = new Criteria();
        criteria.put("configName", "Test");

        Assert.assertEquals(1,sysConfigService.selectByParams(criteria).size());
    }

    @Test
    public void selectByPrimaryKey(){
        SysConfig sysConfig=sysConfigService.selectByPrimaryKey(2L);
        Assert.assertNotNull(sysConfig);
    }

    @Test
    public void countByParams(){
        Criteria criteria = new Criteria();
        criteria.put("configNameLike", "Test");
        criteria.put("configKeyLike","Test");
        Assert.assertEquals(0,sysConfigService.countByParams(criteria));
    }

    @Test
    public void deleteByPrimaryKey(){
        int count=sysConfigService.deleteByPrimaryKey(1L);
        Assert.assertEquals(1,count);
    }
}
