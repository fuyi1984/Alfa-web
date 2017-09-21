package com.alfa.ws.rest.money;

import com.alfa.web.pojo.moneyactivities;
import com.alfa.web.pojo.moneyactivitiesconcern;
import com.alfa.web.service.money.moneyactivitiesServcie;
import com.alfa.web.service.money.moneyactivitiesconcernServcie;
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
 * Created by Administrator on 2017/9/21.
 */
public class moneyactivitiesconcernRestImpl implements moneyactivitiesconcernRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private moneyactivitiesconcernServcie moneyactivitiesconcernService;



    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {
        //region

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

        //过滤
        Criteria criteria = new Criteria();

        WebUtil.preparePageParams(request, pager, criteria, "A.createdDt desc");

        List<moneyactivitiesconcern> moneyactivitiesconcernList = this.moneyactivitiesconcernService.selectByParams(criteria);

        int count = this.moneyactivitiesconcernService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", moneyactivitiesconcernList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();

        //endregion
    }

    @Override
    public Response insertmoneyactivitiesconcern(moneyactivitiesconcern money) {
        return null;
    }

    @Override
    public Response updatemoneyactivitiesconcern(moneyactivitiesconcern money) {
        return null;
    }

    @Override
    public Response batchdeletemoneyactivitiesconcern(List<String> list) {
        return null;
    }
}
