package com.alfa.web;

import com.alfa.web.pojo.HostoryOrderStatus;
import com.alfa.web.service.order.HostoryOrderStatusService;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public class HostoryOrderStatusServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(HostoryOrderStatusServiceTest.class);

    @Autowired
    private HostoryOrderStatusService hostoryOrderStatusService;

    @Test
    public void countByParams(){
        Criteria criteria = new Criteria();
        int count=this.hostoryOrderStatusService.countByParams(criteria);
        System.out.println(count);
    }

    @Test
    public void selectByParams(){
        Criteria criteria = new Criteria();
        List<HostoryOrderStatus> list=this.hostoryOrderStatusService.selectByParams(criteria);
        for (HostoryOrderStatus item:list) {
            System.out.println(item.getRealtotal());
        }
    }
}
