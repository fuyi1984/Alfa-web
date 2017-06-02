package com.alfa.web;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.SysRoleService;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.web.util.pojo.UserManager;
import com.alfa.web.util.pojo.UserSession;
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
    public void InsertUser() throws Exception {

        SysUsers user=new SysUsers();
        user.setUsername("9999");
        user.setPhone("9999");
        user.setRoleId(5L);
        user.setAddress("12345");
        user.setRealname("12345");

        Criteria criteria = new Criteria();
        criteria.put("username", user.getUsername());
        List<SysUsers> UsersList = this.sysUsersService.selectByParams(criteria);

        logger.info("UsersList Size:"+UsersList.size());

        if (UsersList.size() > 0) {
            logger.info(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_EXIST_SUCCESS, null)));
        } else {
            //user.setSexname(user.getSex()=="0"?"男":"女");
            user.setPassword(WebUtil.encrypt(user.getPassword(),user.getUsername()));
            boolean result = this.sysUsersService.insertUser(user);
            if(result){
                //json= InterfaceResult.setResult(WebConstants.ResultStatus.SUCCESS,null);
                logger.info(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.USER_ADD_SUCCESS, null)));
            }else{
                logger.info(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_ADD_FAILURE, null)));
            }
        }
    }

    @Test
    public void current(){
        try{
            // 当前用户信息已在验证用户登录时放入UserManager中
            UserSession currentUser= UserManager.getUserSession();
            if(currentUser!=null&&currentUser.getId()!=null&&currentUser.getUser()!=null){
                System.out.println(currentUser.getUser().getRealname());
            }else{
                currentUser=new UserSession();
                currentUser.setId(null);
                System.out.println(currentUser);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
