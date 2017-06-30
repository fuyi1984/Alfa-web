package com.alfa.web;

import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.VerifyCodeService;
import com.alfa.web.service.weixin_usersService;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/6/30.
 */
public class weixin_usersServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(weixin_usersServiceTest.class);

    @Autowired
    private weixin_usersService weixin_usersService;

    @Test
    public void insert(){
        td_weixin_users td_weixin_users=new td_weixin_users();
        td_weixin_users.setOpenid("1");
        td_weixin_users.setHeadimgurl("1");
        td_weixin_users.setState("0");
        weixin_usersService.insertSelective(td_weixin_users);
    }

    @Test
    public void Search(){
        Criteria criteria = new Criteria();

        criteria.put("openid", "1");

        List<td_weixin_users> list=weixin_usersService.selectByParams(criteria);

        System.out.println(list);
    }

    @Test
    public void Update(){

    }
}
