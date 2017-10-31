package com.alfa.web;

import com.alfa.web.pojo.menurolerelevance;
import com.alfa.web.service.sys.menurolerelevanceService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class menurolerelevanceServiceTest extends TestBase{

    private static Logger logger = Logger.getLogger(menurolerelevanceServiceTest.class);

    @Autowired
    private menurolerelevanceService menurolerelevanceService;


    @Test
    public void selectParam(){

        Criteria criteria=new Criteria();

        List<menurolerelevance> menurolerelevanceServiceList=this.menurolerelevanceService.selectByParams(criteria);

        System.out.println(JsonUtil.toJson(menurolerelevanceServiceList));

    }
}
