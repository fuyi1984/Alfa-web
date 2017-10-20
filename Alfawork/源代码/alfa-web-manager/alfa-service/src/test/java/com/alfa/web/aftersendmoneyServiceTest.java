package com.alfa.web;

import com.alfa.web.service.money.aftersendmoneyService;

import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.vo.calculatemoneyandcount;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class aftersendmoneyServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(aftersendmoneyServiceTest.class);

    @Autowired
    private aftersendmoneyService aftersendmoneyService;

    @Test
    public void selectmoneycountByParams(){
        Criteria criteria=new Criteria();
        criteria.put("openid","oZr2WwVrs0MqAGxPIzk1RNtuXsqE");
        List<calculatemoneyandcount> calculatemoneyandcountList=this.aftersendmoneyService.selectmoneycountByParams(criteria);
        System.out.println(JsonUtil.toJson(calculatemoneyandcountList));
    }
}
