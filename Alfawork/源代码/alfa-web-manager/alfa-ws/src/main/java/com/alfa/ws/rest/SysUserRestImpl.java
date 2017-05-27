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
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            user.setSexname(user.getSex()=="0"?"男":"女");
            user.setPassword(WebUtil.encrypt(user.getPassword(),user.getUsername()));
            int result = this.sysUsersService.insertSelective(user);
            if(result==1){
                //json= InterfaceResult.setResult(WebConstants.ResultStatus.SUCCESS,null);
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, WebConstants.MsgCd.USER_ADD_SUCCESS, null))).build();
            }else{
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, WebConstants.MsgCd.USER_ADD_FAILURE, null))).build();
            }
        }
    }

    @Override
    public Response findUserlist(String param, HttpServletRequest request, HttpServletResponse response) {

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

        if (!StringUtil.isNullOrEmpty(map.get("username"))) {
            criteria.put("usernameLike",  map.get("username").toString());
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
