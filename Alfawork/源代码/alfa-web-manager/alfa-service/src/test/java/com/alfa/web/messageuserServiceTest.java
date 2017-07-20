package com.alfa.web;

import com.alfa.web.pojo.messageuser;
import com.alfa.web.service.messageuserService;
import com.alfa.web.service.publishmessageService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public class messageuserServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(messageuserServiceTest.class);

    @Autowired
    private messageuserService messageuserService;

    @Test
    public void insertSelective(){
       messageuser user=new messageuser();
       user.setUserid(18L);
       user.setMessageid(3L);
       user.setIsread("1");
       this.messageuserService.insertSelective(user);
    }

    @Test
    public void batchdeleteByMessageid(){
        List<String> list=new ArrayList<String>();
        list.add("2");
        this.messageuserService.batchdeleteByMessageid(list);
    }

}
