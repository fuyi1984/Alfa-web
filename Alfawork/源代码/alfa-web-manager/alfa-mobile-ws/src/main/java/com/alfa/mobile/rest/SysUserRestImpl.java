package com.alfa.mobile.rest;


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
import com.alfa.web.vo.ModifyPwdUser;
import com.alfa.web.vo.RegisterUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/11.
 */
public class SysUserRestImpl implements SysUserRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private SysUsersService sysUsersService;

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Override
    public Response getCaptcha(String mobile) {
        Map<String, String> result = null;

        try {
            result = new HashMap<String, String>();
            VerifyCode vc = new VerifyCode();
            vc.setType(WebConstants.VerifyCode.type0);
            vc.setBoundAccount(mobile);
            String code = verifyCodeService.insertVerifyCodeAndReturn(vc);
            result.put("captcha", code);
            log.debug(mobile);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ERROR_MOBILE_GET_FAILURE)).build();
        }
        return Response.status(Response.Status.OK).entity(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.INFO_MOBILE_GET_SUCCESS, result)).build();
    }

    @Override
    public Response createUser(RegisterUser registerUser) {

        MobileUser mu = new MobileUser();

        SysUsers user = new SysUsers();
        user.setPhone(registerUser.getMobile());
        user.setCaptcha(registerUser.getCaptcha());
        user.setVerifyCode(registerUser.getCaptcha());
        user.setUsername(user.getPhone());
        user.setRealname(user.getPhone());

        mu.setUser(user);


        Criteria criteria = new Criteria();
        criteria.put("username", user.getUsername());
        //criteria.put("phone",user.getPhone());

        List<SysUsers> userExistList = this.sysUsersService.selectByParams(criteria);

        if (userExistList.size() > 0) {
            return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                            "帐号已经存在", null))).build();
        }


        criteria.clear();

        criteria.put("code", user.getVerifyCode());
        criteria.put("boundAccount", user.getPhone());
        criteria.put("type", WebConstants.VerifyCode.type0);

        List<VerifyCode> vcList = this.verifyCodeService.selectByParams(criteria);

        if (vcList.size() == 0) {
            return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                            "验证码不正确"))).build();
        }

        //手机号码验证是否注册
        criteria.clear();

        criteria.put("phone", user.getPhone());

        List<SysUsers> userList = this.sysUsersService.selectByParams(criteria);
        if (userList.size() > 0) {
            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.FAILURE, "手机号已经存在", null)
                    )).build();
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

            log.info("User Register: Create Account successfully!");

        } catch (Exception e) {
            log.error(e);
            result = false;
        }

        if (result) {
            log.info("User Register: Create User successfully!");
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "注册成功", null))).build();
        } else {
            log.info("User Register: Create User failed!");
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "注册失败", null))).build();
        }
    }

    @Override
    public Response login(RegisterUser registerUser, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

        Response response = Response.status(500).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "服务器异常，请联系管理员。", null))).build();

        HttpSession session = servletRequest.getSession();

        // 获取手机号和验证码
        String phone = registerUser.getMobile().trim();
        String Captcha = registerUser.getCaptcha().trim();

        //手机号验证码为空返回提示
        if (StringUtil.isNullOrEmpty(phone) || StringUtil.isNullOrEmpty(Captcha)) {
            return Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "请输入手机号和验证码。", null))).build();
        }

        Criteria criteria = new Criteria();

        criteria.put("code", Captcha);
        criteria.put("boundAccount", phone);
        criteria.put("type", WebConstants.VerifyCode.type0);

        List<VerifyCode> vcList = this.verifyCodeService.selectByParams(criteria);

        if (vcList.size() == 0) {
            return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                            "验证码不正确"))).build();
        }

        criteria.clear();

        criteria.put("username", phone);
        criteria.put("phone", phone);

        // 根据用户名密码查询用户信息
        List<SysUsers> users = this.sysUsersService.selectByParams(criteria);

        if (users.size() > 0) {

            SysUsers currentUser = users.get(0);

            String passwordEncrypt = WebUtil.encrypt(Captcha, currentUser.getUsername());

            currentUser.setCaptcha(Captcha);
            currentUser.setVerifyCode(Captcha);
            currentUser.setPassword(passwordEncrypt);
            currentUser.setMobiletoken(StringUtil.getUUID());

            this.sysUsersService.updateByPrimaryKeySelective(currentUser);
            //}
            // 保存Session和Cookie
            String json = JsonUtil.toJson(
                    this.sysUsersService.createSession(session, servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser));
            response = Response.status(Response.Status.OK).entity(json).build();

        } else {
            response = Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "帐号不存在。", null))).build();
        }

        return response;
    }

    @Override
    public Response resetPassword(ModifyPwdUser user) {
        return null;
    }

    @Override
    public Response getUserCenterInfo(Long userSid) {
        return null;
    }
}
