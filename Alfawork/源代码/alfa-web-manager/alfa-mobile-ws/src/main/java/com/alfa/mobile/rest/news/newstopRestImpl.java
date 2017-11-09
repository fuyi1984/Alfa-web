package com.alfa.mobile.rest.news;

import com.alfa.web.pojo.newstop;
import com.alfa.web.service.news.newstopService;
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

public class newstopRestImpl implements newstopRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private newstopService newstopService;

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

        if (!StringUtil.isNullOrEmpty(map.get("titlelike"))) {
            criteria.put("titlelike",  map.get("titlelike").toString());
        }

        if (!StringUtil.isNullOrEmpty(map.get("publishDt"))) {
            criteria.put("publishDt",  map.get("publishDt").toString());
        }

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<newstop> newstopList = this.newstopService.selectByParams(criteria);
        int count = this.newstopService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", newstopList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();
    }
}
