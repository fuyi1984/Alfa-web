package com.alfa.ws.rest.money;

import com.alfa.web.pojo.activitiesorder;
import com.alfa.web.pojo.beforesendmoney;
import com.alfa.web.pojo.moneyactivities;
import com.alfa.web.service.money.activitiesorderService;
import com.alfa.web.service.money.beforesendmoneyService;
import com.alfa.web.service.money.moneyactivitiesServcie;
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

    @Autowired
    private moneyactivitiesServcie moneyactivitiesServcie;


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

        //显示状态
        criteria.put("isvisible","4");
        //启用，停用
        criteria.put("statuslist", "1".split(","));

        //查询正常启用的红包活动
        List<moneyactivities> moneyactivitiesList=this.moneyactivitiesServcie.selectByParams(criteria);


        String activitiesidlist="";

        Map<String, Object> data = new HashMap<String, Object>();

        if(moneyactivitiesList.size()>0){

             for(int i=0;i<moneyactivitiesList.size();i++){
                 if(i==moneyactivitiesList.size()-1){
                     activitiesidlist+=moneyactivitiesList.get(i).getId().toString();
                 }else{
                     activitiesidlist+=moneyactivitiesList.get(i).getId().toString()+",";
                 }
             }

            //region

            criteria.clear();

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

            //手机号
            if(!StringUtil.isNullOrEmpty(map.get("mobile"))){
                criteria.put("mobileLike",map.get("mobile").toString());
            }

            //活动标题
            if(!StringUtil.isNullOrEmpty(map.get("title"))){
                criteria.put("titleLike",map.get("title").toString());
            }

            //活动ID
            if(!StringUtil.isNullOrEmpty(activitiesidlist)){
                criteria.put("activitiesidlist",activitiesidlist.split(","));
            }

            WebUtil.preparePageParams(request, pager, criteria, "A.createdDt desc");

            List<activitiesorder> activitiesorderList = this.activitiesorderService.selectByParams(criteria);
            int count = this.activitiesorderService.countByParams(criteria);

            data.put("total", count);
            data.put("rows", activitiesorderList);

            //endregion
        }

        if(data.isEmpty()){
            data.put("total", "");
            data.put("rows", "");
        }

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

                //region 查询可用的红包活动

                String activitiesidlist="";

                for(int i=0;i<activitiesorderlist.size();i++){
                    if(i==activitiesorderlist.size()-1){
                        activitiesidlist+=activitiesorderlist.get(i).getActivitiesid().toString();
                    }else{
                        activitiesidlist+=activitiesorderlist.get(i).getActivitiesid().toString()+",";
                    }
                }

                criteria.clear();

                //隐藏状态
                criteria.put("isvisiblelist","-4,4".split(","));
                //停用,手动停用
                criteria.put("statuslist", "0,2".split(","));
                //活动id列表
                criteria.put("idlist",activitiesidlist.split(","));

                //查询活动
                List<moneyactivities> moneyactivitiesList=this.moneyactivitiesServcie.selectByParams(criteria);

                if(moneyactivitiesList.size()>0){

                    String activitiestitlelist="";

                    for(int i=0;i<moneyactivitiesList.size();i++){
                        if(i==moneyactivitiesList.size()-1){
                            activitiestitlelist+=moneyactivitiesList.get(i).getTitle().toString();
                        }else{
                            activitiestitlelist+=moneyactivitiesList.get(i).getTitle().toString()+",";
                        }
                    }

                    //活动已经停用
                    return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "4", "\""+activitiestitlelist+"\" 活动已停用或者删除"))).build();
                }

                //endregion


                //region

                beforesendmoney money = new beforesendmoney();

                for (activitiesorder item : activitiesorderlist) {

                    money.setOpenid(item.getOpenid());
                    money.setActivitiesid(item.getActivitiesid());
                    money.setOrderid(item.getOrderid());
                    money.setOrderno(item.getOrderno());
                    money.setMobile(item.getMobile());

                    this.beforesendmoneyService.insertSelective(money);
                }

                //endregionidlistcricri

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
