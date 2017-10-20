package com.alfa.mobile.rest.money;

import com.alfa.web.pojo.aftersendmoney;
import com.alfa.web.service.money.aftersendmoneyService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.web.vo.calculatemoneyandcount;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class aftersendmoneyRestImpl implements aftersendmoneyRest {

    private final Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private aftersendmoneyService aftersendmoneyService;

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

        //手机号
        if(!StringUtil.isNullOrEmpty(map.get("mobile"))){
            criteria.put("mobileLike",map.get("mobile").toString());
        }

        //活动标题
        if(!StringUtil.isNullOrEmpty(map.get("title"))){
            criteria.put("titleLike",map.get("title").toString());
        }

        //openid
        if(!StringUtil.isNullOrEmpty(map.get("openid"))){
            criteria.put("openid",map.get("openid").toString());
        }


        WebUtil.preparePageParams(request, pager, criteria, "A.createdDt desc");

        List<aftersendmoney> aftersendmoneyList = this.aftersendmoneyService.selectByParams(criteria);

        int count = this.aftersendmoneyService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", aftersendmoneyList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Override
    public Response gettotalmoneyandtotalcount(aftersendmoney money) {

        Criteria criteria=new Criteria();

        //openid
        if(!StringUtil.isNullOrEmpty(money.getOpenid())){
            criteria.put("openid",money.getOpenid());
        }

        List<calculatemoneyandcount> calculatemoneyandcountList=this.aftersendmoneyService.selectmoneycountByParams(criteria);

        if(calculatemoneyandcountList.size()>0){
            String json=JsonUtil.toJson(calculatemoneyandcountList);
            //数据存在
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", json))).build();
        }

        //数据不存在
        return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
    }
}
