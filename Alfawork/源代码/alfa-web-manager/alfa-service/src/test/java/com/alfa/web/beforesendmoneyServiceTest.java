package com.alfa.web;

import com.alfa.web.pojo.activitiesorder;
import com.alfa.web.pojo.beforesendmoney;
import com.alfa.web.service.money.activitiesorderService;
import com.alfa.web.service.money.beforesendmoneyService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/22.
 */
public class beforesendmoneyServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(beforesendmoneyServiceTest.class);

    @Autowired
    private beforesendmoneyService beforesendmoneyService;

    @Test
    public void insert(){
        beforesendmoney money=new beforesendmoney();
        money.setOpenid("2");
        money.setActivitiesid(3l);
        money.setOrderid(3l);
        this.beforesendmoneyService.insertSelective(money);
    }

    @Test
    public void search(){
        Criteria criteria=new Criteria();
        List<beforesendmoney> list=this.beforesendmoneyService.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(list));
    }

    @Test
    public void update(){
        beforesendmoney money=new beforesendmoney();
        money.setId(1l);
        money.setOpenid("3");
        money.setActivitiesid(3l);
        money.setOrderid(3l);
        this.beforesendmoneyService.updateByPrimaryKeySelective(money);
    }

    @Test
    public void delete(){
        List<String> list=new ArrayList<String>();
        list.add("1");
        this.beforesendmoneyService.batchdeleteByPrimaryKey(list);
    }
}
