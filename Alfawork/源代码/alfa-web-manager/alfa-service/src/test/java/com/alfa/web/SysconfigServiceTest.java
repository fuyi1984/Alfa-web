package com.alfa.web;

import com.alfa.web.pojo.SysConfig;
import com.alfa.web.service.SysconfigService;
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
}
