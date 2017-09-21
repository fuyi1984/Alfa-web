package com.alfa.web;

import com.alfa.web.pojo.moneyactivities;
import com.alfa.web.pojo.moneyactivitiesconcern;
import com.alfa.web.service.money.moneyactivitiesconcernServcie;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        money.setActivitiesid(2L);
        this.moneyactivitiesconcernServcie.insertSelective(money);
    }

    @Test
    public void search(){
        Criteria criteria = new Criteria();
        List<moneyactivitiesconcern> list=this.moneyactivitiesconcernServcie.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(list));
    }

    @Test
    public void delete(){
       List<String> list=new ArrayList<String>();
       list.add("1");
       this.moneyactivitiesconcernServcie.batchdeleteByPrimaryKey(list);
    }

    @Test
    public void update(){
        moneyactivitiesconcern money=new moneyactivitiesconcern();
        money.setId(2l);
        money.setOpenid("2");
        money.setActivitiesid(2L);
        this.moneyactivitiesconcernServcie.updateByPrimaryKeySelective(money);
    }
}
