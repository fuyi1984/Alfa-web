package com.alfa.web;

import com.alfa.web.pojo.moneyactivities;
import com.alfa.web.service.money.moneyactivitiesServcie;
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
public class moneyactivitiesServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(moneyactivitiesServiceTest.class);

    @Autowired
    private moneyactivitiesServcie moneyactivitiesService;

    @Test
    public void insert(){

        moneyactivities money=new moneyactivities();
        money.setTitle("4411");
        money.setContent("442222");
        this.moneyactivitiesService.insertSelective(money);
    }

    @Test
    public void search(){
        Criteria criteria = new Criteria();
        List<moneyactivities> list=this.moneyactivitiesService.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(list));
    }

    @Test
    public void delete(){
        List<String> list=new ArrayList<String>();
        list.add("1");
        this.moneyactivitiesService.batchdeleteByPrimaryKey(list);
    }

    @Test
    public void update(){
        moneyactivities money=new moneyactivities();
        money.setId(2L);
        money.setTitle("4411");
        money.setContent("442222");
        money.setMoney("11111");
        this.moneyactivitiesService.updateByPrimaryKeySelective(money);
    }
}
