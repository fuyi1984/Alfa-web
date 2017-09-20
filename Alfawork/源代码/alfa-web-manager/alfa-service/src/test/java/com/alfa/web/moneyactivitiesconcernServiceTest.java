package com.alfa.web;

import com.alfa.web.pojo.moneyactivitiesconcern;
import com.alfa.web.service.money.moneyactivitiesconcernServcie;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/9/20.
 */
public class moneyactivitiesconcernServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(moneyactivitiesconcernServiceTest.class);

    @Autowired
    private moneyactivitiesconcernServcie moneyactivitiesconcernServcie;

    @Test
    public void insert(){
        moneyactivitiesconcern money=new moneyactivitiesconcern();
        money.setOpenid("1");
        money.setActivitiesid("2");
        this.moneyactivitiesconcernServcie.insertSelective(money);
    }
}
