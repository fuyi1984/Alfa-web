package com.alfa.ws.rest;

import com.alfa.web.aspect.UserLog;
import com.alfa.web.pojo.SysConfig;
import com.alfa.web.pojo.SysRole;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.SysRoleService;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.constant.MyError;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Response;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/26.
 */
public class SysUserRestImpl implements SysUserRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private SysUsersService sysUsersService;

    @Autowired
    private SysRoleService sysRoleService;


    @Override
    public Response insertByMobile(SysUsers user, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {
        return null;
    }

    @Override
    public Response insertUser(SysUsers user) throws Exception {

        Criteria criteria = new Criteria();
        criteria.put("username", user.getUsername());
        List<SysUsers> UsersList = this.sysUsersService.selectByParams(criteria);

        log.info("UsersList Size:" + UsersList.size());

        if (UsersList.size() > 0) {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_EXIST_SUCCESS, null))).build();
        } else {
            //log.info("user.getSex:"+user.getSex());
            //user.setSexname(user.getSex().equals("0")?"男":"女");
            user.setPassword(WebUtil.encrypt(user.getPassword(), user.getUsername()));
            boolean result = this.sysUsersService.insertUser(user);
            if (result) {
                //json= InterfaceResult.setResult(WebConstants.ResultStatus.SUCCESS,null);
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.USER_ADD_SUCCESS, null))).build();
            } else {
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_ADD_FAILURE, null))).build();
            }
        }
    }

    @Override
    public Response deleteUser(SysUsers user) {
        int result = 0;
        if (!StringUtil.isNullOrEmpty(user.getUserId())) {
            Criteria criteria = new Criteria();
            criteria.put("userId", user.getUserId());
            result = this.sysUsersService.deleteByPrimaryKey(user.getUserId());
        }
        if (result == 1) {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.USER_DELETE_SUCCESS, null))).build();
        } else {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_DELETE_FAILURE, null))).build();
        }
    }

    @Override
    public Response editUser(SysUsers user) {

        WebUtil.prepareUpdateParams(user);

        int result = this.sysUsersService.updateByPrimaryKeySelective(user);
        if (result == 1) {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.USER_EDIT_SUCCESS, null))).build();
        } else {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_EDIT_FAILURE, null))).build();
        }
    }

    @Override
    public Response logoutUser(HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("resultStatus", "false");

        Response response = Response.status(Response.Status.OK).entity(resultMap).build();

        try{
            HttpSession session = servletRequest.getSession();

            if (session != null && session.getId() != null && !"".equals(session.getId())) {
                if (session != null) {

                    Enumeration e = session.getAttributeNames();
                    while (e.hasMoreElements()) {
                        String sessionName = (String) e.nextElement();
                        log.info("logoutUser----------------exits user session name="+sessionName+" sessionId="+session.getId());
                        session.removeAttribute(sessionName);
                    }

                    session.invalidate();
                    resultMap = new HashMap<String, Object>();
                    resultMap.put("resultStatus", "true");
                    return Response.status(Response.Status.OK).entity(resultMap).build();
                }
            }

        }catch (Exception e){
            log.error("user logout error---------------"+e.getMessage());
            e.printStackTrace();
            return response;
        }
        return response;
    }

    @Override
    public Response verifyUser(SysUsers user, HttpServletRequest servletRequest, HttpServletResponse servletResponse) {

        Response response = Response.status(500)
                .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "服务器异常，请联系管理员。", null))).build();

        HttpSession session=servletRequest.getSession();

        String platform=servletRequest.getHeader("PLATFORM");

        //获取用户名和密码

        String account=user.getUsername().trim();
        String password=user.getPassword().trim();

        //用户名密码为空返回提示
        if (StringUtil.isNullOrEmpty(account) || StringUtil.isNullOrEmpty(password)) {
            return Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.error_users_name_isempty, null))).build();
        }

        log.info("verifyUser---account="+account);

        Criteria criteria = new Criteria();
        criteria.put("username", account);
        criteria.put("phone", account);

        // 根据用户名密码查询用户信息
        List<SysUsers> users = sysUsersService.selectByParams(criteria);

        if (users.size() > 0 && users.size() == 1) {

            SysUsers currentUser = users.get(0);

            String passwordEncrypt = WebUtil.encrypt(password, currentUser.getUsername());

            log.info("User Login: "+passwordEncrypt+"@"+currentUser.getPassword()+"@"+password);

            if(currentUser.getPassword().equals(password) || currentUser.getPassword().equals(passwordEncrypt)){
                if(StringUtil.isNullOrEmpty(user.getToken()) || StringUtil.isNullOrEmpty(currentUser.getToken())){
                    currentUser.setToken(StringUtil.getUUID());
                    sysUsersService.updateByPrimaryKeySelective(currentUser);
                }

                // 保存Session和Cookie
                String json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS,WebConstants.MsgCd.users_password_verify_success,sysUsersService.createSession(servletRequest,
                        servletResponse, WebConstants.CURRENT_PLATFORM_USER, currentUser)));
                response = Response.status(Response.Status.OK).entity(json).build();
            }else{
                response = Response.status(Response.Status.OK)
                        .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.error_users_wrong_password, null))).build();
            }
        }else{
            response = Response.status(Response.Status.OK)
                    .entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.error_users_name_notexist, null))).build();

        }

        return response;
    }

    @Override
    public Response findUserlist(String param, HttpServletRequest request, HttpServletResponse response) {

        Map map = WebUtil.getParamsMap(param, "utf-8");
        //分页排序处理
        BasePager pager = new BasePager();

        if (!StringUtil.isNullOrEmpty(map.get("pagenum"))) {
            pager.setPageIndex(Integer.parseInt(map.get("pagenum").toString()));
        }

       /* if (!StringUtil.isNullOrEmpty(map.get("page"))) {
            pager.setPageIndex(Integer.parseInt(map.get("page").toString()));
        }

        if (!StringUtil.isNullOrEmpty(map.get("rows"))) {
            pager.setPageIndex(Integer.parseInt(map.get("rows").toString()));
        }*/

        if (!StringUtil.isNullOrEmpty(map.get("pagesize"))) {
            pager.setPageSize(Integer.parseInt(map.get("pagesize").toString()));
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortdatafield"))) {
            pager.setSortField(map.get("sortdatafield").toString());
        }

       /* if (!StringUtil.isNullOrEmpty(map.get("sort"))) {
            pager.setSortField(map.get("sort").toString());
        }*/

        if (!StringUtil.isNullOrEmpty(map.get("sortName"))) {
            pager.setSortField(map.get("sortName").toString());
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortorder"))) {
            pager.setSortOrder(map.get("sortorder").toString());
        }

        /*if (!StringUtil.isNullOrEmpty(map.get("order"))) {
            pager.setSortOrder(map.get("order").toString());
        }*/

        //过滤
        Criteria criteria = new Criteria();

        if (!StringUtil.isNullOrEmpty(map.get("username"))) {
            criteria.put("usernameLike", map.get("username").toString());
        }
       /* if (!StringUtil.isNullOrEmpty(map.get("roleId"))) {
            criteria.put("roleId",  map.get("roleId").toString());
        }*/

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<SysUsers> sysUsersList = this.sysUsersService.selectByParams(criteria);

        int count = this.sysUsersService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", sysUsersList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();

    }

    @Override
    public Response current(HttpServletRequest servletRequest) {
        try{
            log.debug("start");
            // 当前用户信息已在验证用户登录时放入UserManager中
            UserSession currentUser= UserManager.getUserSession();

            log.debug("start2");
            if(currentUser!=null&&currentUser.getId()!=null&&currentUser.getUser()!=null){
                return Response.status(Response.Status.OK).entity(currentUser).build();
            }else{
                currentUser=new UserSession();
                currentUser.setId(null);
                return Response.status(Response.Status.OK).entity(currentUser).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("").build();
        }
    }

    @Override
    public Response modifyPassword(String params) {

        String json="";
        Map<String,Object> map;

        MyError error = new MyError();
        error.error = "修改密码失败 ";
        error.errorCode = "320";

        try{
            map=JsonUtil.fromJson(params,Map.class);
            Long userId = Long.parseLong(map.get("userId").toString());
            SysUsers currentUser = this.sysUsersService.selectByPrimaryKey(userId);

            String newPwd=String.valueOf(map.get("password"));
            String oldPwd=String.valueOf(map.get("oldPassword"));

            if(!WebUtil.encrypt(oldPwd,currentUser.getUsername()).equals(currentUser.getPassword())){
                return Response.status(Response.Status.OK).entity(
                        JsonUtil.toJson(
                                new RestResult(RestResult.FAILURE,
                                        WebConstants.MsgCd.error_users_original_pwd,
                                        null))).build();
            }

            currentUser.setPassword(WebUtil.encrypt(newPwd, currentUser.getUsername()));
            WebUtil.prepareUpdateParams(currentUser);
            int result=this.sysUsersService.updateByPrimaryKeySelective(currentUser);

            if (result == 1) {
                return Response.status(Response.Status.OK).entity(
                        JsonUtil.toJson(
                                new RestResult(RestResult.SUCCESS,
                                        WebConstants.MsgCd.password_update_success,
                                        null))).build();
            } else {
                return Response.status(320).entity(error).build();
            }
        }catch (Exception e){
            e.printStackTrace();
            return Response.status(320).entity(error).build();
        }
    }
}
