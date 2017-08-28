package com.alfa.web;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.VerifyCode;
import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.sys.SysUsersService;
import com.alfa.web.service.sms.VerifyCodeService;
import com.alfa.web.util.*;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.vo.MobileUser;
import com.alfa.web.vo.RegisterUser;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @Autowired
    private com.alfa.web.service.weixin.weixin_usersService weixin_usersService;

    @Test
    public void createuser(){
        MobileUser mu = new MobileUser();

        SysUsers user = new SysUsers();

        RegisterUser registerUser=new RegisterUser();
        registerUser.setMobile("18580043708");
        registerUser.setCaptcha("254389");
        //registerUser.setOrgname("阿尔发");

        user.setPhone(registerUser.getMobile());

        user.setCaptcha(registerUser.getCaptcha());
        user.setVerifyCode(registerUser.getCaptcha());

        user.setUsername(user.getPhone());
        user.setRealname(user.getPhone());

        //单位名称
        //user.setOrgname(registerUser.getOrgname());

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

    @Test
    public void loginforweixin() throws ParseException {

        // 获取手机号,验证码和OpenId
        String phone ="15320295813";
        String Captcha = "751047";
        String openid = "";

        //region 手机号和验证码判断

        //手机号验证码为空返回提示
        if (StringUtil.isNullOrEmpty(phone) || StringUtil.isNullOrEmpty(Captcha)) {
            System.out.println("请输入手机号和验证码。");
        }

        //endregion

        //region OpenId为空返回提示

        if(StringUtil.isNullOrEmpty(openid))
        {
            //OpenId不能为空
            System.out.println("OpenId不能为空.");
        }

        //endregion

        Criteria criteria = new Criteria();

        //region 手机验证码判断

        criteria.put("code", Captcha);
        criteria.put("boundAccount", phone);
        criteria.put("type", WebConstants.VerifyCode.type0);

        List<VerifyCode> vcList = this.verifyCodeService.selectByParams(criteria);

        if (vcList.size() == 0) {
            System.out.println("验证码不正确");
        } else {

            //region 手机验证码有效时间判断

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date now = df.parse(df.format(new Date()));

            VerifyCode code = vcList.get(0);
            Date start = code.getCreatedDt();

            long between = (now.getTime() - start.getTime()) / 1000;

            if (between > Long.parseLong(PropertiesUtil.getProperty("sms.verify.Valid.time"))) {
                System.out.println("验证码已经超过有效时间");
            }

            //endregion
        }

        //endregion

        criteria.clear();

        //region 用户信息判断

        criteria.put("username", phone);
        criteria.put("phone", phone);

        // 根据用户名密码查询用户信息
        List<SysUsers> users = this.sysUsersService.selectByParamsForWeixin(criteria);

        if (users.size() > 0) {

            //region 用户信息不为空

            SysUsers currentUser = users.get(0);

            //region 查询OpenId

            criteria.clear();

            criteria.put("openid",openid);

            List<td_weixin_users> weixinlist=this.weixin_usersService.selectByParams(criteria);

            if(weixinlist.size()>0){

                td_weixin_users weixin=weixinlist.get(0);

                if(StringUtil.isNullOrEmpty(currentUser.getWeixinid())){
                    currentUser.setWeixinid(weixin.getId());
                }else{
                    if(!currentUser.getWeixinid().equals(weixin.getId())){
                        currentUser.setWeixinid(weixin.getId());
                    }
                }
            }

            //endregion

            currentUser.setCaptcha(Captcha);
            currentUser.setVerifyCode(Captcha);

            /**
             * 角色为产废单位的时候用验证码替换用户密码
             */
            if (currentUser.getRoleId().equals(10L)) {
                String passwordEncrypt = WebUtil.encrypt(Captcha, currentUser.getUsername());
                currentUser.setPassword(passwordEncrypt);
            }

            currentUser.setMobiletoken(StringUtil.getUUID());
            currentUser.setLoginIp("");

            this.sysUsersService.updateByPrimaryKeySelective(currentUser);

            //}
            // 保存Session和Cookie
            //String json = JsonUtil.toJson(
            //        this.sysUsersService.createSession(session, servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser));

            currentUser.setPassword("");
            currentUser.setCaptcha("");
            currentUser.setVerifyCode("");
            currentUser.setToken("");

            String json = JsonUtil.toJson(currentUser);

            System.out.println(json);

            //endregion

        } else {

            //region 用户信息为空

            System.out.println("帐号不存在。");

            //endregion
        }

        //endregion

    }
}
