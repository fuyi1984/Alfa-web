package com.alfa.web;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.userregisterbehavior;
import com.alfa.web.service.userregisterbehaviorService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
public class userregisterbehaviorServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(userregisterbehaviorServiceTest.class);

    @Autowired
    private userregisterbehaviorService userregisterbehaviorService;

    @Test
    public void insertSelective(){

        userregisterbehavior record=new userregisterbehavior();
        //业务人员ID
        record.setUserid(18L);
        //注册用户的ID
        record.setRegisterid(19L);

        int result=this.userregisterbehaviorService.insertSelective(record);
        if(result==1){
            System.out.println("1");
        }else{
            System.out.println("2");
        }
    }

    @Test
    public void selectByParams(){
        Criteria criteria = new Criteria();
        criteria.put("businessphone","15320295813");
        criteria.put("businessrealname","付益");
        List<userregisterbehavior> userregisterbehaviorlist = this.userregisterbehaviorService.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(userregisterbehaviorlist));
    }

    @Test
    public void countByParams(){
        Criteria criteria = new Criteria();
        criteria.put("businessphone","15320295813");
        criteria.put("businessrealname","付益");
        int count = this.userregisterbehaviorService.countByParams(criteria);
        System.out.println(count);
    }

    @Test
    public void batchdeleteByPrimaryKey(){
       List<String> list=new ArrayList<String>();
       list.add("2");
       this.userregisterbehaviorService.batchdeleteByPrimaryKey(list);
    }
}
