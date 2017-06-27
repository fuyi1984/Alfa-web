package com.alfa.web;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.VerifyCode;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.service.VerifyCodeService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.web.vo.MobileUser;
import com.alfa.web.vo.RegisterUser;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Administrator on 2017/6/27.
 */
public class mobileuserTest extends TestBase {

    private static Logger logger = Logger.getLogger(mobileuserTest.class);

    @Autowired
    private SysUsersService sysUsersService;

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Test
    public void createuser(){
        MobileUser mu = new MobileUser();

        SysUsers user = new SysUsers();

        RegisterUser registerUser=new RegisterUser();
        registerUser.setMobile("18580043708");
        registerUser.setCaptcha("254389");
        registerUser.setOrgname("阿尔发");

        user.setPhone(registerUser.getMobile());

        user.setCaptcha(registerUser.getCaptcha());
        user.setVerifyCode(registerUser.getCaptcha());

        user.setUsername(user.getPhone());
        user.setRealname(user.getPhone());

        //单位名称
        user.setOrgname(registerUser.getOrgname());

        mu.setUser(user);


        Criteria criteria = new Criteria();
        criteria.put("username", user.getUsername());
        //criteria.put("phone",user.getPhone());

        List<SysUsers> userExistList = this.sysUsersService.selectByParams(criteria);

        if (userExistList.size() > 0) {
            System.out.println("帐号已经存在");
        }


        criteria.clear();

        criteria.put("code", user.getVerifyCode());
        criteria.put("boundAccount", user.getPhone());
        criteria.put("type", WebConstants.VerifyCode.type0);

        List<VerifyCode> vcList = this.verifyCodeService.selectByParams(criteria);

        if (vcList.size() == 0) {
            System.out.println("验证码不正确");
        }

        //手机号码验证是否注册
        criteria.clear();

        criteria.put("phone", user.getPhone());

        List<SysUsers> userList = this.sysUsersService.selectByParams(criteria);
        if (userList.size() > 0) {
            System.out.println("手机号已经存在");
        }

        //添加数据
        boolean result = true;

        try {

            user.setToken(StringUtil.getUUID());
            user.setMobiletoken(StringUtil.getUUID());
            user.setPassword(WebUtil.encrypt(user.getVerifyCode(), user.getUsername()));
            user.setRoleId(10L);

            result = this.sysUsersService.inserMobileUser(user);

            //user.setPassword("");

            logger.info("User Register: Create Account successfully!");

        } catch (Exception e) {
            logger.error(e);
            result = false;
        }

        if (result) {
            logger.info("User Register: Create User successfully!");

            System.out.println("注册成功");
        } else {
            logger.info("User Register: Create User failed!");

            System.out.println("注册失败");
        }
    }
}
