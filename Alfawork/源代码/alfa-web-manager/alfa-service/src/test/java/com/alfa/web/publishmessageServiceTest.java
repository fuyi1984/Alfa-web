package com.alfa.web;

import com.alfa.web.pojo.publishmessage;
import com.alfa.web.service.message.publishmessageService;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public class publishmessageServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(publishmessageServiceTest.class);

    @Autowired
    private publishmessageService publishmessageService;

    @Test
    public void insertSelective(){
        publishmessage p=new publishmessage();
        p.setTitle("111111123");
        p.setContent("22222234");
        this.publishmessageService.insertSelective(p);
    }

    @Test
    public void selectByParams()
    {
        Criteria criteria = new Criteria();
        criteria.put("userid","18");
        criteria.put("isread","0");
        List<publishmessage> list=this.publishmessageService.selectByParams(criteria);
        Assert.assertEquals(1,list.size());
    }

    @Test
    public void countByParams(){
        Criteria criteria = new Criteria();
        criteria.put("userid","18");
        criteria.put("isread","1");
        int count=this.publishmessageService.countByParams(criteria);
        Assert.assertEquals(2,count);
    }

    @Test
    public void batchdeleteByPrimaryKey(){
        List<String> list=new ArrayList<String>();
        list.add("1");
        this.publishmessageService.batchdeleteByPrimaryKey(list);
    }

    @Test
    public void updateByPrimaryKeySelective(){
        publishmessage p=new publishmessage();
        p.setId(2L);
        p.setContent("222222345555");
        this.publishmessageService.updateByPrimaryKeySelective(p);
    }
}
