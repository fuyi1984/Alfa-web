package com.alfa.ws.rest.money;

import com.alfa.web.pojo.aftersendmoney;
import com.alfa.web.pojo.aftersendmoneyfailture;
import com.alfa.web.service.money.aftersendmoneyService;
import com.alfa.web.service.money.aftersendmoneyfailtureService;
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

public class aftersendmoneyfailtureRestImpl implements aftersendmoneyfailtureRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private aftersendmoneyfailtureService aftersendmoneyfailtureService;

    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {
        Map map = WebUtil.getParamsMap(param, "utf-8");

        //分页排序处理
        BasePager pager = new BasePager();

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

        //提交开始时间
        if(!StringUtil.isNullOrEmpty(map.get("startDt"))){
            criteria.put("createDtFrom",map.get("startDt").toString()+" 00:00:00");
        }

        //提交结束时间
        if(!StringUtil.isNullOrEmpty(map.get("endDt"))){
            criteria.put("createDtTo",map.get("endDt").toString()+" 23:59:59");
        }

        WebUtil.preparePageParams(request, pager, criteria, "A.createdDt desc");

        List<aftersendmoneyfailture> aftersendmoneyfailtureList = this.aftersendmoneyfailtureService.selectByParams(criteria);

        int count = this.aftersendmoneyfailtureService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", aftersendmoneyfailtureList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();
    }
}
