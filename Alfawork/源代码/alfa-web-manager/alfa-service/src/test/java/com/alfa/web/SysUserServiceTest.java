package com.alfa.web;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.SysRoleService;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
public class SysUserServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(SysUserServiceTest.class);

    @Autowired
    private SysUsersService sysUsersService;

    @Test
    public void InsertUser(){

        SysUsers user=new SysUsers();
        user.setUsername("12345");
        user.setPhone("12345");
        user.setSex("0");
        user.setRoleId(6L);
        user.setAddress("12345");
        user.setRealname("12345");

        Criteria criteria = new Criteria();
        criteria.put("username", user.getUsername());
        List<SysUsers> UsersList = this.sysUsersService.selectByParams(criteria);

        logger.info("UsersList Size:"+UsersList.size());

        if (UsersList.size() > 0) {
            logger.info(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_EXIST_SUCCESS, null)));
        } else {
            user.setSexname(user.getSex()=="0"?"男":"女");
            user.setPassword(WebUtil.encrypt(user.getPassword(),user.getUsername()));
            int result = this.sysUsersService.insertSelective(user);
            if(result==1){
                //json= InterfaceResult.setResult(WebConstants.ResultStatus.SUCCESS,null);
                logger.info(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.USER_ADD_SUCCESS, null)));
            }else{
                logger.info(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_ADD_FAILURE, null)));
            }
        }
    }
}
