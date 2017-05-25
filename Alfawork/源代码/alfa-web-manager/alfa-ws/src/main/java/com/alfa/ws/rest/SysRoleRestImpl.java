package com.alfa.ws.rest;

import com.alfa.web.pojo.SysRole;
import com.alfa.web.service.SysRoleService;
import com.alfa.web.service.SysconfigService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.ws.rest.SysRoleRest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Administrator on 2017/4/27.
 */
public class SysRoleRestImpl implements SysRoleRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public Response findRole(SysRole role) {
        Criteria example = new Criteria();

        if(!StringUtil.isNullOrEmpty(role.getRole_name())){
            example.put("roleNameLike", role.getRole_name());
        }
        if(!StringUtil.isNullOrEmpty(role.getId())){
            example.put("roleSids", role.getId());
        }

        List<SysRole> list = this.sysRoleService.selectByParams(example);

        String json = JsonUtil.toJson(list);
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Override
    public Response addRole(SysRole role) {
        Criteria criteria = new Criteria();
        criteria.put("roleName", role.getRole_name());
        List<SysRole> roleList = this.sysRoleService.selectByParams(criteria);
        if (roleList.size() > 0) {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ERROR_ROLES_EXISTS, null))).build();
        }else{
            int result = this.sysRoleService.insertSelective(role);
            if(result==1){
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.ROLES_ADD_SUCCESS, null))).build();
            } else {
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ROLES_ADD_FAILURE, null))).build();
            }
        }
    }

    @Override
    public Response editRole(SysRole role) {
        Criteria criteria = new Criteria();
        criteria.put("roleName", role.getRole_name());
        criteria.put("unRoleSid", role.getId());
        List<SysRole> roleList = this.sysRoleService.selectByParams(criteria);
        if (roleList.size() > 0) {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ERROR_ROLES_EXISTS, null))).build();
        }else{
            int result = this.sysRoleService.updateByPrimaryKeySelective(role);
            if(result==1){
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.ROLES_ADD_SUCCESS, null))).build();
            } else {
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ROLES_ADD_FAILURE, null))).build();
            }
        }
    }

    @Override
    public Response deleteRole(SysRole role) {
        int result = 0;
        if (!StringUtil.isNullOrEmpty(role.getId())) {
            Criteria criteria = new Criteria();
            criteria.put("roleSid", role.getId());
            /*List<UserRole> userRole = userRoleService.selectByParams(criteria);
            if(!StringUtil.isNullOrEmpty(userRole) && userRole.size()>0){
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ERROR_ROLE_USED), null))).build();
            }*/
            result = this.sysRoleService.deleteByPrimaryKey(role.getId());
        }
        if(result==1){
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.ROLES_ADD_SUCCESS, null))).build();
        } else {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ROLES_ADD_FAILURE, null))).build();
        }
    }

    @Override
    public Response findAllRole() {
        Criteria example = new Criteria();
        example.put("status", "0");
        example.setOrderByClause("SORT ASC");
        List<SysRole> list = this.sysRoleService.selectByParams(example);
        String json = JsonUtil.toJson(list);
        return Response.status(Response.Status.OK).entity(json).build();
    }
}
