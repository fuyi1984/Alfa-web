package com.alfa.ws.rest.money;

import com.alfa.web.pojo.activitiesorder;
import com.alfa.web.pojo.beforesendmoney;
import com.alfa.web.service.money.activitiesorderService;
import com.alfa.web.service.money.beforesendmoneyService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class activitiesorderRestImpl implements activitiesorderRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private activitiesorderService activitiesorderService;

    @Autowired
    private beforesendmoneyService beforesendmoneyService;


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

        criteria.put("isfinish","3");
        criteria.put("visible","4");

        //提交开始时间
        if(!StringUtil.isNullOrEmpty(map.get("startDt"))){
            criteria.put("createDtFrom",map.get("startDt").toString()+" 00:00:00");
        }

        //提交结束时间
        if(!StringUtil.isNullOrEmpty(map.get("endDt"))){
            criteria.put("createDtTo",map.get("endDt").toString()+" 23:59:59");
        }

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<activitiesorder> activitiesorderList = this.activitiesorderService.selectByParams(criteria);
        int count = this.activitiesorderService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", activitiesorderList );

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Override
    public Response sendmoney(List<String> list) {

        Criteria criteria = new Criteria();

        List<beforesendmoney> beforesendmoneyList=this.beforesendmoneyService.selectByParams(criteria);

        if(beforesendmoneyList.size()==0) {

            //Map map = WebUtil.getParamsMap(param, "utf-8");

            String idlist= StringUtils.collectionToDelimitedString(list, ",");

            if (!StringUtil.isNullOrEmpty(idlist)) {
                criteria.put("idlist",idlist.split(","));
            }

            List<activitiesorder> activitiesorderlist = this.activitiesorderService.selectByParams(criteria);

            if (activitiesorderlist.size() > 0) {

                beforesendmoney money = new beforesendmoney();

                for (activitiesorder item : activitiesorderlist) {

                    money.setOpenid(item.getOpenid());
                    money.setActivitiesid(item.getActivitiesid());
                    money.setOrderid(item.getOrderid());
                    money.setOrderno(item.getOrderno());
                    money.setMobile(item.getMobile());

                    this.beforesendmoneyService.insertSelective(money);
                }

                //发送成功
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
            }

            //没有发送的订单
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }else{
            //数据已经存在
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
        }
    }
}
