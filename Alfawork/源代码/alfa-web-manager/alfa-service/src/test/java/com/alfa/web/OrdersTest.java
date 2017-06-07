package com.alfa.web;

import com.alfa.web.pojo.Orders;
import com.alfa.web.service.OrdersService;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.util.WebUtil;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/6/7.
 */
public class OrdersTest extends TestBase {

    private static Logger logger = Logger.getLogger(OrdersTest.class);

    @Autowired
    private OrdersService ordersService;

    @Test
    public void Add() throws Exception {

        Orders orders=new Orders();
        orders.setUsername("付益");
        orders.setIphone("15320295813");
        orders.setAddress("重庆市沙坪坝区陈家桥镇");
        orders.setNum("100");
        orders.setOrgname("阿尔发石油化工");
        orders.setOrgstatus("1");
        orders.setWorkerid(18L);

        Assert.assertEquals(true,this.ordersService.insert(orders));
    }

    @Test
    public void Update(){
        Orders orders=new Orders();
        orders.setOrderid(2L);
        orders.setWorkerid(18L);

        WebUtil.prepareUpdateParams(orders);

        Assert.assertEquals(1,this.ordersService.updateByPrimaryKeySelective(orders));
    }

    @Test
    public void Delete(){
        Orders orders=new Orders();
        orders.setOrderid(2L);

        Assert.assertEquals(true,this.ordersService.delete(orders.getOrderid()));
    }

}
