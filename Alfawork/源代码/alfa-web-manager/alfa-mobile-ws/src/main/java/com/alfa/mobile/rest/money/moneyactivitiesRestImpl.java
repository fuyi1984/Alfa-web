package com.alfa.mobile.rest.money;

import com.alfa.web.pojo.moneyactivities;
import com.alfa.web.pojo.moneyactivitiesconcern;
import com.alfa.web.service.money.moneyactivitiesServcie;
import com.alfa.web.service.money.moneyactivitiesconcernServcie;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/20.
 */
public class moneyactivitiesRestImpl implements moneyactivitiesRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private moneyactivitiesServcie moneyactivitiesService;

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

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<moneyactivities> moneyactivitiesList = this.moneyactivitiesService.selectByParams(criteria);
        int count = this.moneyactivitiesService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", moneyactivitiesList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();

        //endregion
    }

    @Override
    public Response insertmoneyactivities(moneyactivities money) {
        //region

        Criteria criteria = new Criteria();
        criteria.put("titlelike", money.getTitle());
        List<moneyactivities> moneyactivitiesList = this.moneyactivitiesService.selectByParams(criteria);

        if (moneyactivitiesList.size() > 0) {
            //数据已存在
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "1", null))).build();
        } else {
            int result = this.moneyactivitiesService.insertSelective(money);
            if (result > 0) {
                //插入成功
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "2", null))).build();
            } else {
                //插入失败
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
            }
        }

        //endregion
    }

    @Override
    public Response updatemoneyactivities(moneyactivities money) {

        //region

        String Json="";

        int result=this.moneyactivitiesService.updateByPrimaryKeySelective(money);

        if(result==1){
            //更新成功
            Json=JsonUtil.toJson(new RestResult(RestResult.SUCCESS,"1",null));
        }else{
            //更新失败
            Json=JsonUtil.toJson(new RestResult(RestResult.FAILURE,"2",null));
        }

        return Response.status(Response.Status.OK).entity(Json).build();

        //endregion
    }

    @Override
    public Response batchdeletemoneyactivities(List<String> list) {
        //region
        int result = 0;

        result=this.moneyactivitiesService.batchdeleteByPrimaryKey(list);

        if (result >= 1) {
            //删除成功
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
        } else {
            //删除失败
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }
        //endregion
    }

    @Override
    public Response isNotGetRedMoeny(moneyactivitiesconcern money) {

        Criteria criteria = new Criteria();

        Date dt = new Date();

        criteria.put("id", money.getActivitiesid());

        List<moneyactivities> moneyactivitieslist=this.moneyactivitiesService.selectByParams(criteria);

        if(moneyactivitieslist.size()>0){

            moneyactivities activities=moneyactivitieslist.get(0);

            if (activities.getStarttime().compareTo(dt)==-1&& activities.getEndtime().compareTo(dt)==1) {

                //region 时间有效期范围内

                //活动启用
                if (activities.getStatus().equals("1")) {

                    //region

                    criteria.clear();
                    //criteria.put("openid", money.getOpenid());
                    criteria.put("activitiesid", money.getActivitiesid());

                    int count = this.moneyactivitiesconcernService.countByParams(criteria);

                    if (count < Integer.parseInt(activities.getTotalnum())) {

                        //region 判断用户是否已经关注了红包活动

                        criteria.clear();
                        criteria.put("openid", money.getOpenid());
                        criteria.put("activitiesid", money.getActivitiesid());

                        count = this.moneyactivitiesconcernService.countByParams(criteria);

                        //endregion

                        //用户已经关注过红包活动
                        if (count > 0) {
                            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
                        } else {
                            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
                        }
                    }
                    //活动的红包总数已经领完
                    else {
                        return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
                    }

                    //endregion
                }
                //活动手动停用
                else if (activities.getStatus().equals("2")) {
                    return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
                }
                //活动停用
                else {
                    return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
                }

                //endregion
            }else{
                //region 时间有效期范围外
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
                //endregion
            }

        }else {
            //活动不存在
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }
    }
}
