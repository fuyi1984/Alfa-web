package com.alfa.ws.rest;

import com.alfa.web.aspect.UserLog;
import com.alfa.web.pojo.SysConfig;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.service.SysRoleService;
import com.alfa.web.service.SysUsersService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
public class SysUserRestImpl implements SysUserRest{

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
    public Response insertUser(SysUsers user) {

        Criteria criteria = new Criteria();
        criteria.put("username", user.getUsername());
        List<SysUsers> UsersList = this.sysUsersService.selectByParams(criteria);

        if (UsersList.size() > 0) {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_EXIST_SUCCESS, null))).build();
        } else {
            int result = this.sysUsersService.insertSelective(user);
            if(result==1){
                //json= InterfaceResult.setResult(WebConstants.ResultStatus.SUCCESS,null);
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.USER_ADD_SUCCESS, null))).build();
            }else{
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_ADD_FAILURE, null))).build();
            }
        }
    }
}
