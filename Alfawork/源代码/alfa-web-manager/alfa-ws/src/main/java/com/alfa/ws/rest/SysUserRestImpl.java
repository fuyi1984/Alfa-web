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
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.web.util.pojo.UserSession;
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
        return null;
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
}
