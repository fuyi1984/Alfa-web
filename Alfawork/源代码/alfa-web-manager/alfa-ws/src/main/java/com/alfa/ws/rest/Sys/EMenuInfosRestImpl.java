package com.alfa.ws.rest.Sys;

import com.alfa.web.pojo.EMenuInfos;
import com.alfa.web.pojo.menurolerelevance;
import com.alfa.web.service.sys.EMenuInfosService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.MenuUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.web.vo.Menus;
import com.alfa.web.vo.treedata;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.*;

/**
 * Created by Administrator on 2017/7/30.
 */
public class EMenuInfosRestImpl implements EMenuInfosRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private EMenuInfosService eMenuInfosService;


    @Override
    public Response insertmenu(EMenuInfos menu) throws Exception {

        Criteria criteria = new Criteria();
        criteria.put("cascadeid",menu.getCascadeid());
        criteria.put("menuname", menu.getMenuname());
        //criteria.put("url", menu.getUrl());

        List<EMenuInfos> eMenuInfosList = this.eMenuInfosService.selectByParams(criteria);

        if(eMenuInfosList.size()==0){
            int result=this.eMenuInfosService.insertSelective(menu);
            if(result>0){
                //插入成功
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS,"2", null))).build();
            }else{
                //插入失败
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,"3", null))).build();
            }
        }else{
            //数据已存在
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE,"1", null))).build();
        }
    }

    @Override
    public Response batchdeletemenu(List<String> list) {
        int result = 0;

        result=this.eMenuInfosService.batchdeleteByPrimaryKey(list);

        if (result >= 1) {
            //插入成功
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
        } else {
            //插入失败
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }
    }

    @Override
    public Response editmenu(EMenuInfos menu) {

//        Criteria criteria = new Criteria();
//        criteria.put("menuname", menu.getMenuname());
//        criteria.put("url", menu.getUrl());
//
//        List<EMenuInfos> eMenuInfosList = this.eMenuInfosService.selectByParams(criteria);
//
//        if(eMenuInfosList.size()>0){
            int result=this.eMenuInfosService.updateByPrimaryKeySelective(menu);
            if(result>0){
                //更新成功
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
            }else{
                //更新失败
                return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
            }
//        }else{
//            //数据不存在
//            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "3", null))).build();
//        }
    }

    @Override
    public Response findist(String param, HttpServletRequest request, HttpServletResponse response) {

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

        if (!StringUtil.isNullOrEmpty(map.get("bmenuname"))) {
            criteria.put("bmenuname",  map.get("bmenuname").toString());
        }

       /* if (!StringUtil.isNullOrEmpty(map.get("url"))) {
            criteria.put("urlLike",  map.get("url").toString());
        }*/

        WebUtil.preparePageParams(request, pager, criteria, "createdDt desc");

        List<EMenuInfos> eMenuInfosList = this.eMenuInfosService.selectByParams(criteria);
        int count = this.eMenuInfosService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", eMenuInfosList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Override
    public Response findtreeMenu(String param, HttpServletRequest request, HttpServletResponse response) {

        Map map= WebUtil.getParamsMap(param,"utf-8");

        BasePager pager=new BasePager();

        Criteria criteria = new Criteria();

        WebUtil.preparePageParams(request, pager, criteria, "parentid");

        List<EMenuInfos> eMenuInfosList = this.eMenuInfosService.selectByParams(criteria);

        List<treedata> treedataList=new ArrayList<treedata>();

        for (EMenuInfos item : eMenuInfosList) {

            treedata tree=new treedata();

            tree.setId(Long.parseLong(item.getCascadeid()));
            tree.setParentId(Long.parseLong(item.getParentid()));
            tree.setText(item.getMenuname());

            treedataList.add(tree);
        }

        String json = JsonUtil.toJson(MenuUtil.buildTreeByRecursive(treedataList));

        return Response.status(Response.Status.OK).entity(json).build();
    }
}
