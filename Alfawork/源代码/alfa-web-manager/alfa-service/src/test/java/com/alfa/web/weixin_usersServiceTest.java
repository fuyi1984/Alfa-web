package com.alfa.web;

import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.weixin.weixin_usersService;
import com.alfa.web.util.JsonUtil;
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
        td_weixin_users.setOpenid("onsayw7BLsbVN3TzO-Nysb4Mda4M");
        td_weixin_users.setHeadimgurl("http://wx.qlogo.cn/mmopen/nHRjFTqkrZ06TYVQBNeiar5ffduz4OYFVdbx78eDUNIYN3SRruPRoOFib9icekOnic8c0h998SKicibib51OmmCtaIYazT2trsuxZZX/0");
        td_weixin_users.setState("0");
        weixin_usersService.insertSelective(td_weixin_users);
    }

    @Test
    public void Search(){
        Criteria criteria = new Criteria();

        criteria.put("roleId", "9");

        List<td_weixin_users> list=weixin_usersService.selectByParams(criteria);

        System.out.println(JsonUtil.toJson(list));
    }

    @Test
    public void selectByParams(){
        Criteria criteria = new Criteria();

        criteria.put("mobile", "13883906635");

        List<td_weixin_users> list=weixin_usersService.selectByParamsForMobile(criteria);

        System.out.println(JsonUtil.toJson(list));
    }

    @Test
    public void Update(){
        td_weixin_users td_weixin_users=new td_weixin_users();
        td_weixin_users.setId(1L);
        td_weixin_users.setUserid(1L);
        System.out.println(weixin_usersService.updateByPrimaryKeySelective(td_weixin_users));
    }

    @Test
    public void delete(){
        System.out.println(weixin_usersService.deleteByPrimaryKey(1L));
    }
}
