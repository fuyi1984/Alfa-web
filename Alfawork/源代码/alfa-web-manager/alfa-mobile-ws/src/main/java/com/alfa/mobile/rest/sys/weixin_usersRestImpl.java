package com.alfa.mobile.rest.sys;

import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.order.OrdersService;
import com.alfa.web.service.weixin.weixin_usersService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/30.
 */
public class weixin_usersRestImpl implements weixin_usersRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private weixin_usersService weixin_usersService;

    @Autowired
    private OrdersService ordersService;

    @Override
    public Response insertOpenId(td_weixin_users td_weixin_users) throws Exception {

        Criteria criteria = new Criteria();
        criteria.put("openid", td_weixin_users.getOpenid());

        List<td_weixin_users> list=this.weixin_usersService.selectByParams(criteria);

        if(list.size()>=1){
            //OpenId已存在
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "1", null))).build();
        }else{
            int result=this.weixin_usersService.insertSelective(td_weixin_users);
            if(result>=1){
                //OpenId插入成功
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "2", null))).build();
            }else{
                //OpenId插入失败
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
            }
        }
    }

    @Override
    public Response updateOpenId(td_weixin_users td_weixin_users) throws UnsupportedEncodingException   {

        Criteria criteria = new Criteria();
        criteria.put("openid", td_weixin_users.getOpenid());

        List<td_weixin_users> list=this.weixin_usersService.selectByParams(criteria);

        if(list.size()>=1) {
            String Json = "";

            int result = this.weixin_usersService.updateByPrimaryKeySelective(td_weixin_users);

            if (result >= 1) {
                //OpenId更新成功
                Json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null));
            } else {
                //OpenId更新失败
                Json = JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null));
            }

            return Response.status(Response.Status.OK).entity(Json).build();
        }else{
            //OpenId不存在
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
        }
    }

    @Override
    public Response GetSingleOpenId(td_weixin_users td_weixin_users) throws UnsupportedEncodingException {

        Criteria criteria = new Criteria();
        criteria.put("openid", td_weixin_users.getOpenid());

        List<td_weixin_users> list=this.weixin_usersService.selectByParams(criteria);

        if(list.size()>=1) {
            td_weixin_users users=list.get(0);
            //查询成功
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", users))).build();

        }else{
            //查询失败
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }
    }

    @Override
    public Response GetSingleOpenIdForMobile(td_weixin_users td_weixin_users) throws UnsupportedEncodingException {

        Criteria criteria = new Criteria();
        criteria.put("mobile", td_weixin_users.getMobile());
        //criteria.put("mobiletoken",td_weixin_users.getMobiletoken());

        List<td_weixin_users> list=this.weixin_usersService.selectByParamsForMobile(criteria);

        if(list.size()>=1) {

            td_weixin_users users=list.get(0);

            //查询成功
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", users))).build();

        }else{
            //查询失败
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }
    }

    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {

        //region

        Map map= WebUtil.getParamsMap(param,"utf-8");

        BasePager pager=new BasePager();

        if (!StringUtil.isNullOrEmpty(map.get("pagenum"))) {
            pager.setPageIndex(Integer.parseInt(map.get("pagenum").toString()));
        }

        if (!StringUtil.isNullOrEmpty(map.get("pagesize"))) {
            pager.setPageSize(Integer.parseInt(map.get("pagesize").toString()));
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortdatafield"))) {
            pager.setSortField(map.get("sortdatafield").toString());
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortName"))) {
            pager.setSortField(map.get("sortName").toString());
        }

        if (!StringUtil.isNullOrEmpty(map.get("sortorder"))) {
            pager.setSortOrder(map.get("sortorder").toString());
        }

        Criteria criteria = new Criteria();

        if (!StringUtil.isNullOrEmpty(map.get("nickname"))) {
            criteria.put("nickname",  map.get("nickname").toString());
        }
        if (!StringUtil.isNullOrEmpty(map.get("roleId"))) {
            criteria.put("roleId",  map.get("roleId").toString());
        }

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<td_weixin_users> td_weixin_usersList = this.weixin_usersService.selectByParams(criteria);
        int count = this.weixin_usersService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", td_weixin_usersList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();

        //endregion
    }
}
