package com.alfa.ws.rest.Sys;

import com.alfa.web.pojo.EMenuInfos;
import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.weixin.weixin_usersService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/7.
 */
public class weixin_usersRestImpl implements weixin_usersRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private com.alfa.web.service.weixin.weixin_usersService weixin_usersService;


    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {

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
            criteria.put("nicknamelike",  map.get("nickname").toString());
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
    }
}
