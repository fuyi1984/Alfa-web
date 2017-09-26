package com.alfa.web;

import com.alfa.web.pojo.activitiesorder;
import com.alfa.web.service.money.activitiesorderService;
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
public class activitiesorderServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(activitiesorderServiceTest.class);

    @Autowired
    private activitiesorderService activitiesorderService;

    @Test
    public void insert(){
        activitiesorder order=new activitiesorder();
        order.setOpenid("2");
        order.setActivitiesid(3l);
        order.setOrderid(3l);
        order.setIsfollow("0");
        this.activitiesorderService.insertSelective(order);
    }

    @Test
    public void search(){
        Criteria criteria=new Criteria();
        List<activitiesorder> list=this.activitiesorderService.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(list));
    }

    @Test
    public void update(){
        activitiesorder order=new activitiesorder();
        order.setId(1l);
        order.setOpenid("2");
        order.setActivitiesid(3l);
        order.setOrderid(3l);
        order.setIsfollow("0");
        this.activitiesorderService.updateByPrimaryKeySelective(order);
    }

    @Test
    public void delete(){
        List<String> list=new ArrayList<String>();
        list.add("1");
        this.activitiesorderService.batchdeleteByPrimaryKey(list);
    }
}
