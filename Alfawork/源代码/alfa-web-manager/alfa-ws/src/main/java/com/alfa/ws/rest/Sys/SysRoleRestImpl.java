package com.alfa.ws.rest.Sys;

import com.alfa.web.pojo.SysRole;
import com.alfa.web.service.SysRoleService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        if (!StringUtil.isNullOrEmpty(role.getRole_name())) {
            example.put("roleNameLike", role.getRole_name());
        }
        if (!StringUtil.isNullOrEmpty(role.getRoleId())) {
            example.put("roleId", role.getRoleId());
        }

        List<SysRole> list = this.sysRoleService.selectByParams(example);

        String json = JsonUtil.toJson(list);
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Override
    public Response addRole(SysRole role) {
        Criteria criteria = new Criteria();
        criteria.put("roleNameLike", role.getRole_name());
        List<SysRole> roleList = this.sysRoleService.selectByParams(criteria);
        if (roleList.size() > 0) {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ERROR_ROLES_EXISTS, null))).build();
        } else {
            int result = this.sysRoleService.insertSelective(role);
            if (result == 1) {
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.ROLES_ADD_SUCCESS, null))).build();
            } else {
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ROLES_ADD_FAILURE, null))).build();
            }
        }
    }

    @Override
    public Response editRole(SysRole role) {

        //WebUtil.prepareUpdateParams(role);

        int result = this.sysRoleService.updateByPrimaryKeySelective(role);
        if (result == 1) {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.ROLES_EDIT_SUCCESS, null))).build();
        } else {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ROLES_EDIT_FAILURE, null))).build();
        }

    }

    @Override
    public Response deleteRole(SysRole role) {
        int result = 0;
        if (!StringUtil.isNullOrEmpty(role.getRoleId())) {
            Criteria criteria = new Criteria();
            criteria.put("roleId", role.getRoleId());
            /*List<UserRole> userRole = userRoleService.selectByParams(criteria);
            if(!StringUtil.isNullOrEmpty(userRole) && userRole.size()>0){
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ERROR_ROLE_USED), null))).build();
            }*/
            result = this.sysRoleService.deleteByPrimaryKey(role.getRoleId());
        }
        if (result == 1) {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.ROLES_DELETE_SUCCESS, null))).build();
        } else {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ROLES_DELETE_FAILURE, null))).build();
        }
    }

    @Override
    public Response batchdeleteRole(List<String> list) {
        int result = 0;

        result=this.sysRoleService.batchdeleteByPrimaryKey(list);

        if (result >= 1) {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.ROLES_DELETE_SUCCESS, null))).build();
        } else {
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.ROLES_DELETE_FAILURE, null))).build();
        }
    }

    @Override
    public Response findAllRole() {
        Criteria example = new Criteria();
        /*example.put("status", "0");
        example.setOrderByClause("SORT ASC");*/
        List<SysRole> list = this.sysRoleService.selectByParams(example);
        String json = JsonUtil.toJson(list);
        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Override
    public Response findRolelist(String param, HttpServletRequest request, HttpServletResponse response) {

        Map map= WebUtil.getParamsMap(param,"utf-8");
        //分页排序处理
        BasePager pager=new BasePager();

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

        if (!StringUtil.isNullOrEmpty(map.get("roleName"))) {
            criteria.put("roleNameLike",  map.get("roleName").toString());
        }
       /* if (!StringUtil.isNullOrEmpty(map.get("roleId"))) {
            criteria.put("roleId",  map.get("roleId").toString());
        }*/

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<SysRole> configList = this.sysRoleService.selectByParams(criteria);

        int count = this.sysRoleService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", configList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();

    }
}
