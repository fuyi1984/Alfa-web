package com.alfa.mobile.rest.sys;


import com.alfa.web.pojo.*;
import com.alfa.web.service.sms.VerifyCodeService;
import com.alfa.web.service.sys.SysUsersService;
import com.alfa.web.util.*;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.web.util.pojo.UserManager;
import com.alfa.web.util.pojo.UserSession;
import com.alfa.web.vo.MobileUser;
import com.alfa.web.vo.ModifyPwdUser;
import com.alfa.web.vo.RegisterUser;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

    @Autowired
    private com.alfa.web.service.weixin.weixin_usersService weixin_usersService;

    @Autowired
    private com.alfa.web.service.message.messageuserService messageuserService;

    @Autowired
    private com.alfa.web.service.message.publishmessageService publishmessageService;

    @Autowired
    private com.alfa.web.service.file.fileinfoService fileinfoService;

    @Override
    public Response getCaptcha(String mobile) {

        //region 短信发送

        //Map<String, String> result = null;

        String code = "";

        try {
            //result = new HashMap<String, String>();
            VerifyCode vc = new VerifyCode();
            vc.setType(WebConstants.VerifyCode.type0);
            vc.setBoundAccount(mobile);
            code = verifyCodeService.insertVerifyCodeAndReturn(vc);
            //result.put("captcha", code);
            log.debug(mobile);
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ERROR_MOBILE_GET_FAILURE)).build();
        }
        return Response.status(Response.Status.OK).entity(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.INFO_MOBILE_GET_SUCCESS, code)).build();

        //endregion
    }

    /**
     * 收运人员获取手机验证码(登录)
     *
     * @param mobile
     * @return
     */
    @Override
    public Response getCaptchaForWorker(String mobile) {

        //region 收运人员获取手机验证码

        String code = "";

        Criteria criteria = new Criteria();
        criteria.put("phone", mobile);

        List<SysUsers> userList = this.sysUsersService.selectByParams(criteria);

        if (userList.size() > 0) {

            SysUsers users = userList.get(0);

            if (users.getRoleId().equals(9L)) {
                //region 角色为收运人员
                try {
                    VerifyCode vc = new VerifyCode();
                    vc.setType(WebConstants.VerifyCode.type0);
                    vc.setBoundAccount(mobile);
                    code = verifyCodeService.insertVerifyCodeAndReturn(vc);
                } catch (Exception e) {
                    e.printStackTrace();
                    // 短信发送失败
                    return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "1")).build();
                }
                //短信发送成功
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.SUCCESS, "2", code)).build();
                //endregion
            } else {
                //region 角色非收运人员,不能获取手机验证码
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "3")).build();
                //endregion
            }
        } else {
            //region 手机号不存在，不能获取手机验证码
            return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "4")).build();
            //endregion
        }

        //endregion
    }

    /**
     * 产废单位获取手机验证码(注册)
     *
     * @param mobile
     * @return
     */
    @Override
    public Response getCaptchaForFactory(String mobile) {

        //region 产废单位获取手机验证码

        String code = "";

        Criteria criteria = new Criteria();
        criteria.put("phone", mobile);

        List<SysUsers> userList = this.sysUsersService.selectByParams(criteria);

        if (userList.size() > 0) {

            //SysUsers users = userList.get(0);

            /*if (users.getRoleId().equals(9L)) {
                //region 角色为收运人员,不能获取短信验证码
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "1")).build();
                //endregion
            }*/
            //手机号已经存在，不能获取短信验证码
            return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "1")).build();
        }

        //endregion

        //region 短信发送

        try {
            VerifyCode vc = new VerifyCode();
            vc.setType(WebConstants.VerifyCode.type0);
            vc.setBoundAccount(mobile);
            code = verifyCodeService.insertVerifyCodeAndReturn(vc);
        } catch (Exception e) {
            e.printStackTrace();
            // 短信发送失败
            return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "2")).build();
        }
        //短信发送成功
        return Response.status(Response.Status.OK).entity(new RestResult(RestResult.SUCCESS, "3", code)).build();

        //endregion
    }

    /**
     * 产废单位获取手机验证码(登录)
     *
     * @param mobile
     * @return
     */
    @Override
    public Response getCaptchaForFactoryForLogin(String mobile) {
        //region 产废单位获取手机验证码

        String code = "";

        Criteria criteria = new Criteria();
        criteria.put("phone", mobile);

        List<SysUsers> userList = this.sysUsersService.selectByParams(criteria);

        //手机号已存在
        if (userList.size() > 0) {

            SysUsers users = userList.get(0);

            //角色为产废单位
            if (users.getRoleId().equals(10L)) {

                //region 短信发送(登录)

                try {
                    VerifyCode vc = new VerifyCode();
                    vc.setType(WebConstants.VerifyCode.type0);
                    vc.setBoundAccount(mobile);
                    code = verifyCodeService.insertVerifyCodeAndReturn(vc);
                } catch (Exception e) {
                    e.printStackTrace();
                    // 短信发送失败
                    return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "2")).build();
                }

                //endregion

                //短信发送成功
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.SUCCESS, "3", code)).build();


            } else {
                //region 角色非产废单位,不能获取短信验证码
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "1")).build();
                //endregion
            }
        } else {
            //region 手机号不存在,不能获取短信验证码
            //return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "4")).build();
            //endregion

            //region 短信发送(注册)

            try {
                VerifyCode vc = new VerifyCode();
                vc.setType(WebConstants.VerifyCode.type0);
                vc.setBoundAccount(mobile);
                code = verifyCodeService.insertVerifyCodeAndReturn(vc);
            } catch (Exception e) {
                e.printStackTrace();
                // 短信发送失败
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "20")).build();
            }
            //短信发送成功
            return Response.status(Response.Status.OK).entity(new RestResult(RestResult.SUCCESS, "30", code)).build();

            //endregion
        }

        //endregion
    }

    @Override
    public Response createUser(RegisterUser registerUser) throws ParseException {

        MobileUser mu = new MobileUser();

        SysUsers user = new SysUsers();

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
          /*  return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                            "帐号已经存在", null))).build();*/
            return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                            "1", null))).build();
        }


        criteria.clear();

        criteria.put("code", user.getVerifyCode());
        criteria.put("boundAccount", user.getPhone());
        criteria.put("type", WebConstants.VerifyCode.type0);

        List<VerifyCode> vcList = this.verifyCodeService.selectByParams(criteria);

        if (vcList.size() == 0) {
            /*return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                            "验证码不正确"))).build();*/
            return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                            "2"))).build();
        } else {

            //region 手机验证码有效时间判断

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date now = df.parse(df.format(new Date()));

            VerifyCode code = vcList.get(0);
            Date start = code.getCreatedDt();

            long between = (now.getTime() - start.getTime()) / 1000;

            if (between > Long.parseLong(PropertiesUtil.getProperty("sms.verify.Valid.time"))) {
                /*return Response
                        .status(Response.Status.OK)
                        .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                                "验证码已经超过有效时间"))).build();*/
                return Response
                        .status(Response.Status.OK)
                        .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                                "6"))).build();
            }

            //endregion
        }

        //手机号码验证是否注册
        criteria.clear();

        criteria.put("phone", user.getPhone());

        List<SysUsers> userList = this.sysUsersService.selectByParams(criteria);
        if (userList.size() > 0) {
            /*return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.FAILURE, "手机号已经存在", null)
                    )).build();*/

            return Response.status(Response.Status.OK).entity(
                    JsonUtil.toJson(
                            new RestResult(RestResult.FAILURE, "3", null)
                    )).build();
        }

        //添加数据
        boolean result = true;

        try {

            user.setToken(StringUtil.getUUID());
            user.setMobiletoken(StringUtil.getUUID());
            user.setPassword(WebUtil.encrypt(user.getVerifyCode(), user.getUsername()));
            user.setRoleId(10L);
            //设置用户状态为未审核
            user.setStatus("0");

            result = this.sysUsersService.inserMobileUser(user);

            //user.setPassword("");

            log.info("User Register: Create Account successfully!");

        } catch (Exception e) {
            log.error(e);
            result = false;
        }

        if (result) {
            log.info("User Register: Create User successfully!");
            /*return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "注册成功", null))).build();*/
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "4", null))).build();
        } else {
            log.info("User Register: Create User failed!");
            /*return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "注册失败", null))).build();*/
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "5", null))).build();
        }
    }

    @Override
    public Response login(RegisterUser registerUser, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ParseException {

        /*Response response = Response.status(500).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "服务器异常，请联系管理员。", null))).build();*/

        Response response = Response.status(500).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "1", null))).build();

        HttpSession session = servletRequest.getSession();

        // 获取手机号和验证码
        String phone = new String(Base64Util.decode(registerUser.getMobile().trim()));
        String Captcha = new String(Base64Util.decode(registerUser.getCaptcha().trim()));

        //手机号验证码为空返回提示
        if (StringUtil.isNullOrEmpty(phone) || StringUtil.isNullOrEmpty(Captcha)) {
            /*return Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "请输入手机号和验证码。", null))).build();*/
            return Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }

        Criteria criteria = new Criteria();

        criteria.put("code", Captcha);
        criteria.put("boundAccount", phone);
        criteria.put("type", WebConstants.VerifyCode.type0);

        List<VerifyCode> vcList = this.verifyCodeService.selectByParams(criteria);

        if (vcList.size() == 0) {
            /*return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                            "验证码不正确"))).build();*/
            return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                            "3"))).build();
        } else {

            //region 手机验证码有效时间判断

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date now = df.parse(df.format(new Date()));

            VerifyCode code = vcList.get(0);
            Date start = code.getCreatedDt();

            long between = (now.getTime() - start.getTime()) / 1000;

            if (between > Long.parseLong(PropertiesUtil.getProperty("sms.verify.Valid.time"))) {
                /*return Response
                        .status(Response.Status.OK)
                        .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                                "验证码已经超过有效时间"))).build();*/
                return Response
                        .status(Response.Status.OK)
                        .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                                "4"))).build();
            }

            //endregion
        }

        criteria.clear();

        criteria.put("username", phone);
        criteria.put("phone", phone);

        // 根据用户名密码查询用户信息
        List<SysUsers> users = this.sysUsersService.selectByParams(criteria);

        if (users.size() > 0) {

            SysUsers currentUser = users.get(0);


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
            currentUser.setLoginIp(WebUtil.GetCustomIpAddr(servletRequest));

            this.sysUsersService.updateByPrimaryKeySelective(currentUser);

            this.sysUsersService.createSession(servletRequest,
                    servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser);
            //}
            // 保存Session和Cookie
            //String json = JsonUtil.toJson(
            //        this.sysUsersService.createSession(session, servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser));

            currentUser.setPassword("");
            currentUser.setCaptcha("");
            currentUser.setVerifyCode("");
            currentUser.setToken("");

            String json = JsonUtil.toJson(currentUser);

            response = Response.status(Response.Status.OK).entity(json).build();

        } else {
            /*response = Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "帐号不存在。", null))).build();*/
            response = Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "5", null))).build();
        }

        return response;

    }

    /**
     * 微信端手机登录
     * @param registerUser
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws ParseException
     */
    @Override
    public Response loginforweixin(RegisterUser registerUser, HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ParseException {
        /*Response response = Response.status(500).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "服务器异常，请联系管理员。", null))).build();*/

        Response response = Response.status(500).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "1", null))).build();

        HttpSession session = servletRequest.getSession();

        // 获取手机号,验证码,logintype和OpenId

        String phone = new String(Base64Util.decode(registerUser.getMobile().trim()));
        String Captcha = new String(Base64Util.decode(registerUser.getCaptcha().trim()));
        String logintype = new String(Base64Util.decode(registerUser.getLogintype().trim()));

        //String phone = registerUser.getMobile().trim();
        //String Captcha = registerUser.getCaptcha().trim();

        String openid = registerUser.getOpenid().trim();

        //region 手机号和验证码判断

        //手机号验证码为空返回提示

        if (StringUtil.isNullOrEmpty(phone) || StringUtil.isNullOrEmpty(Captcha)) {
            /*return Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "请输入手机号和验证码。", null))).build();*/
            return Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }

        //endregion

        //region OpenId为空返回提示

        if (StringUtil.isNullOrEmpty(openid)) {
            //OpenId不能为空
            return Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "6", null))).build();
        }

        //endregion

        Criteria criteria = new Criteria();

        //region 手机验证码判断

        criteria.put("code", Captcha);
        criteria.put("boundAccount", phone);
        criteria.put("type", WebConstants.VerifyCode.type0);

        List<VerifyCode> vcList = this.verifyCodeService.selectByParams(criteria);

        if (vcList.size() == 0) {
            /*return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                            "验证码不正确"))).build();*/
            return Response
                    .status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                            "3"))).build();
        } else {

            //region 手机验证码有效时间判断

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date now = df.parse(df.format(new Date()));

            VerifyCode code = vcList.get(0);
            Date start = code.getCreatedDt();

            long between = (now.getTime() - start.getTime()) / 1000;

            if (between > Long.parseLong(PropertiesUtil.getProperty("sms.verify.Valid.time"))) {
                /*return Response
                        .status(Response.Status.OK)
                        .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                                "验证码已经超过有效时间"))).build();*/
                return Response
                        .status(Response.Status.OK)
                        .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,
                                "4"))).build();
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

            if (currentUser.getRoleId().equals(Long.parseLong(logintype))) {

                if (!StringUtil.isNullOrEmpty(currentUser.getStatus())) {

                    if (currentUser.getStatus().equals("1")) {

                        String token=StringUtil.getUUID();

                        //region 用户已审核

                        //region 查询OpenId

                        criteria.clear();

                        criteria.put("openid", openid);

                        List<td_weixin_users> weixinlist = this.weixin_usersService.selectByParams(criteria);

                        if (weixinlist.size() > 0) {

                            //region 用户信息关联微信账号信息

                            td_weixin_users weixin = weixinlist.get(0);
                            weixin.setMobile(phone);
                            weixin.setMobiletoken(token);

                            this.weixin_usersService.updateByPrimaryKeySelective(weixin);

                            currentUser.setWeixinid(weixin.getId());
                            currentUser.setOpenid(weixin.getOpenid());
                            currentUser.setHeadimgurl(weixin.getHeadimgurl());
                            currentUser.setState(weixin.getState());

                            //endregion
                        } else {
                            currentUser.setOpenid("");
                            currentUser.setHeadimgurl("");
                            currentUser.setState("");
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

                        currentUser.setMobiletoken(token);
                        currentUser.setLoginIp(WebUtil.GetCustomIpAddr(servletRequest));

                        this.sysUsersService.updateByPrimaryKeySelective(currentUser);

                        //}
                        // 保存Session和Cookie
                        //String json = JsonUtil.toJson(
                        //        this.sysUsersService.createSession(session, servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser));

                        currentUser.setPassword("");
                        currentUser.setCaptcha("");
                        currentUser.setVerifyCode("");
                        currentUser.setToken("");

                        //this.sysUsersService.createSession(session, servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser);

                        this.sysUsersService.createSession(servletRequest,
                                servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser);

                        //region 获取消息通知列表并与用户做绑定,并把消息置为未读

                        /*

                        //region 获取消息通知列表

                        criteria.clear();

                        List<publishmessage> publishmessageList = this.publishmessageService.selectByParams(criteria);

                        //endregion

                        //region 获取未读消息与用户的关系列表

                        criteria.clear();

                        //userid
                        criteria.put("userid", currentUser.getUserId());

                        //isread
                        criteria.put("isread", "0");


                        List<messageuser> messageuserList = this.messageuserService.selectByParams(criteria);

                        //endregion

                        List<messageuser> messageusers=new ArrayList<messageuser>();

                        for (int j = 0; j < publishmessageList.size(); j++) {

                            for (int i = 0; i < messageuserList.size(); i++) {

                                if (publishmessageList.get(j).getId() == messageuserList.get(i).getMessageid()) {

                                }
                            }
                        }

                        int result = this.messageuserService.Batchinsert(messageusers);

                        if (result >= 1) {
                            log.info("批量插入成功!");
                        } else {
                            log.info("批量插入失败!");
                        }

                        */

                        //endregion

                        String json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS,
                                "7", currentUser));

                        //String json= JsonUtil.toJson(this.sysUsersService.createSession(session, servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser));

                        response = Response.status(Response.Status.OK).entity(json).build();

                    } else {
                        //用户未审核
                        response = Response.status(Response.Status.OK)
                                .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "10", null))).build();
                    }
                } else {
                    //用户未审核
                    response = Response.status(Response.Status.OK)
                            .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "10", null))).build();
                }
            } else {
                //角色不一致
                response = Response.status(Response.Status.OK)
                        .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "15", null))).build();
            }

            //endregion

        } else {

            //region 用户信息为空

            /*response = Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "帐号不存在。", null))).build();*/
            response = Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "5", null))).build();

            //endregion
        }

        //endregion

        return response;
    }

    @Override
    public Response loginforwap(SysUsers user, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

        //服务器异常，请联系管理员
        Response response = Response.status(500)
                .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "500", null))).build();

        HttpSession session = servletRequest.getSession();

        //获取用户名和密码

        String account = new String(Base64Util.decode(user.getUsername().trim()));
        String password = new String(Base64Util.decode(user.getPassword().trim()));

        //String account = user.getUsername().trim();
        //String password = user.getPassword().trim();

        //用户名密码为空返回提示
        if (StringUtil.isNullOrEmpty(account) || StringUtil.isNullOrEmpty(password)) {
            return Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "1", null))).build();
        }

        Criteria criteria = new Criteria();
        criteria.put("username", account);
        criteria.put("phone", account);

        // 根据用户名密码查询用户信息
        List<SysUsers> users = sysUsersService.selectByParams(criteria);

        if (users.size() > 0) {

            SysUsers currentUser = users.get(0);

            if (currentUser.getRoleId().equals(19L)) {

                //region 角色为业务人员

                if (!StringUtil.isNullOrEmpty(currentUser.getStatus())) {

                    if (currentUser.getStatus().equals("1")) {

                        //region 已审核

                        String passwordEncrypt = WebUtil.encrypt(password, currentUser.getUsername());

                        if (currentUser.getPassword().equals(password) || currentUser.getPassword().equals(passwordEncrypt)) {

                            //region

                            currentUser.setMobiletoken(StringUtil.getUUID());
                            currentUser.setLoginIp(WebUtil.GetCustomIpAddr(servletRequest));

                            sysUsersService.updateByPrimaryKeySelective(currentUser);

                            currentUser.setPassword("");
                            currentUser.setCaptcha("");
                            currentUser.setVerifyCode("");
                            currentUser.setToken("");

                            //this.sysUsersService.createSession(session, servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser);

                            /*this.sysUsersService.createSession(servletRequest,
                                    servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser);

                            String json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS,
                                    "7", currentUser));*/

                            String json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS,
                                    "7", this.sysUsersService.createSession(servletRequest,
                                    servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser)));

                            response = Response.status(Response.Status.OK).entity(json).build();

                            //endregion
                        }else{
                            //密码错误
                            response = Response.status(Response.Status.OK)
                                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "11", null))).build();
                        }

                        //endregion

                    } else {
                        //用户未审核
                        response = Response.status(Response.Status.OK)
                                .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "10", null))).build();
                    }

                } else {
                    //用户未审核
                    response = Response.status(Response.Status.OK)
                            .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "10", null))).build();
                }

                //endregion
            }else{
                //角色非业务人员，不能登录
                response = Response.status(Response.Status.OK)
                        .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "8", null))).build();
            }
        }else{
            //手机号不存在
            response = Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "5", null))).build();
        }

        return response;
    }

    @Override
    public Response logoutUser(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("resultStatus", "false");

        Response response = Response.status(Response.Status.OK).entity(resultMap).build();

        try {
            HttpSession session = servletRequest.getSession();

            if (session != null && session.getId() != null && !"".equals(session.getId())) {
                if (session != null) {

                    Enumeration e = session.getAttributeNames();
                    while (e.hasMoreElements()) {
                        String sessionName = (String) e.nextElement();
                        log.info("logoutUser----------------exits user session name=" + sessionName + " sessionId=" + session.getId());
                        session.removeAttribute(sessionName);
                    }

                    session.invalidate();
                    resultMap = new HashMap<String, Object>();
                    resultMap.put("resultStatus", "true");
                    return Response.status(Response.Status.OK).entity(resultMap).build();
                }
            }

        } catch (Exception e) {
            log.error("user logout error---------------" + e.getMessage());
            e.printStackTrace();
            return response;
        }
        return response;
    }

    /**
     * 验证用户是否登录
     */
    @Override
    public Response current(HttpServletRequest servletRequest) {
        try {
            log.debug("start");
            // 当前用户信息已在验证用户登录时放入UserManager中
            UserSession currentUser = UserManager.getUserSession();

            log.debug("start2");
            if (currentUser != null && currentUser.getId() != null && currentUser.getUser() != null) {
                return Response.status(Response.Status.OK).entity(currentUser).build();
            } else {
                currentUser = new UserSession();
                currentUser.setId(null);
                return Response.status(Response.Status.OK).entity(currentUser).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("").build();
        }
    }

    @Override
    public Response GetCurrentUserInfoForWeiXin(SysUsers user) {

        Criteria criteria = new Criteria();

        criteria.put("userId", user.getUserId());

        List<SysUsers> users = this.sysUsersService.selectByParamsForWeixin(criteria);

        if (users.size() > 0) {

            SysUsers currentuser = users.get(0);

            //region 关键参数赋值为空

            currentuser.setPassword("");
            currentuser.setCaptcha("");
            currentuser.setVerifyCode("");
            currentuser.setToken("");
            currentuser.setMobiletoken("");

            //endregion

            //用户信息获取成功
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", currentuser))).build();
        } else {
            //用户信息获取失败
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }
    }

    @Override
    public Response editUser(SysUsers user) {

        //region 文件信息表的数据插入
        fileinfo fileinfo=new fileinfo();

        if(!StringUtil.isNullOrEmpty(user.getRegurl()))
        {
            File file=new File(user.getRegurl());

            fileinfo.setUrl(user.getRegurl());
            fileinfo.setType("image");
            fileinfo.setFile_name(file.getName());

            int result=this.fileinfoService.insertSelective(fileinfo);

            if(result==1){
                log.info("文件信息表插入成功!");
            }else{
                log.info("文件信息表插入失败!");
            }
        }
        //endregion

        if(!StringUtil.isNullOrEmpty(fileinfo.getId())){
            user.setFileid(fileinfo.getId());
        }

        Criteria criteria=new Criteria();
        criteria.put("username", user.getPhone());
        criteria.put("phone", user.getPhone());

        List<SysUsers> users = sysUsersService.selectByParams(criteria);

        if(users.size() == 0) {

            //region  没有查询到相同的手机号

            user.setUsername(user.getPhone());

            WebUtil.prepareUpdateParams(user);

            int result = this.sysUsersService.updateByPrimaryKeySelective(user);

            if (result == 1) {
                //用户信息更新成功
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
            } else {
                //用户信息更新失败
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
            }

            //endregion

        }else{

            //region 查询到相同的手机号

            SysUsers tmp=users.get(0);

            if(!tmp.getUserId().equals(user.getUserId())){

                //联系电话已存在
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();

            }else{

                user.setUsername(user.getPhone());

                WebUtil.prepareUpdateParams(user);

                int result = this.sysUsersService.updateByPrimaryKeySelective(user);

                if (result == 1) {
                    //用户信息更新成功
                    return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
                } else {
                    //用户信息更新失败
                    return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
                }
            }

            //endregion
        }
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
