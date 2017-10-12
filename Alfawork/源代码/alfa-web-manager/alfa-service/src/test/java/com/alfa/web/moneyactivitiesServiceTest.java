package com.alfa.web;

import com.alfa.web.pojo.moneyactivities;
import com.alfa.web.service.money.moneyactivitiesServcie;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
public class moneyactivitiesServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(moneyactivitiesServiceTest.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    SimpleDateFormat fullsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private moneyactivitiesServcie moneyactivitiesService;

    @Test
    public void insert() throws ParseException {

        moneyactivities money=new moneyactivities();
        money.setTitle("4411");
        money.setContent("442222");

        money.setStarttime(new Date());
        money.setEndtime(new Date());

        System.out.println(sdf.format(money.getStarttime()));
        System.out.println(sdf.format(money.getStarttime()));

        String starttime=sdf.format(money.getStarttime())+" 00:00:00";
        String endtime=sdf.format(money.getStarttime())+" 23:59:59";

        money.setStarttime(fullsdf.parse(starttime));
        money.setEndtime(fullsdf.parse(endtime));

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
