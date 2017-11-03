package com.alfa.ws.rest.Sys;

import com.alfa.web.pojo.menurolerelevance;
import com.alfa.web.service.sys.EMenuInfosService;
import com.alfa.web.service.sys.menurolerelevanceService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.MenuUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import com.alfa.web.vo.Menus;
import org.apache.log4j.Logger;
import org.apache.commons.lang.StringUtils;
import org.python.apache.xerces.xs.StringList;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.*;

public class menurolerelevanceRestImpl implements menurolerelevanceRest {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * 配置Service
     */
    @Autowired
    private menurolerelevanceService menurolerelevanceService;

    @Autowired
    private EMenuInfosService eMenuInfosService;


    @Override
    public Response findMenu(String param, HttpServletRequest request, HttpServletResponse response) {

        Map map = WebUtil.getParamsMap(param, "utf-8");

        BasePager pager = new BasePager();

        //region

        /*if (!StringUtil.isNullOrEmpty(map.get("pagenum"))) {
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
        }*/

        //endregion

        Criteria criteria = new Criteria();

        if (!StringUtil.isNullOrEmpty(map.get("roleid"))) {
            criteria.put("roleid", map.get("roleid").toString());
        }

        WebUtil.preparePageParams(request, pager, criteria, "B.cascadeid");

        List<menurolerelevance> menurolerelevanceServiceList = this.menurolerelevanceService.selectByParams(criteria);

        List<Menus> MenusList = new ArrayList<Menus>();

        for (menurolerelevance item : menurolerelevanceServiceList) {
            Menus menus = new Menus();
            menus.setMenuId(Long.parseLong(item.getCascadeid()));
            menus.setParentId(Long.parseLong(item.getParentid()));
            menus.setMenuName(item.getMenuname());
            menus.setIcon(item.getIcon());
            menus.setUrl(item.getUrl());
            menus.setCreateTime(new Date());
            MenusList.add(menus);
        }

        String json = JsonUtil.toJson(MenuUtil.buildByRecursive(MenusList));

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Override
    public Response BatchInsertMenu(String param, HttpServletRequest request, HttpServletResponse response) {

        Map map = WebUtil.getParamsMap(param, "utf-8");

        BasePager pager = new BasePager();

        Criteria criteria = new Criteria();

        if (!StringUtil.isNullOrEmpty(map.get("menuidlist"))) {
            criteria.put("menuidlist", map.get("menuidlist").toString().split(","));
        }

        if (!StringUtil.isNullOrEmpty(map.get("roleid"))) {
            criteria.put("roleid", map.get("roleid").toString());
        }

        List<menurolerelevance> menurolerelevanceList = this.menurolerelevanceService.selectByParams(criteria);

        if (menurolerelevanceList.size() > 0) {

            //region

            List<String> Stringlist = new ArrayList<String>();

            for (menurolerelevance m : menurolerelevanceList) {
                if (!StringUtil.isNullOrEmpty(m.getMenuname())) {
                    if (!Stringlist.contains(m.getMenuname())) {
                        Stringlist.add(m.getMenuname());
                    }
                }
            }

            if (Stringlist.size() > 0) {
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "3", "\"" + StringUtils.join(Stringlist, ",") + "\" 已分配给所选角色！")).build();
            } else {
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "2", null)).build();
            }

            //endregion

        } else {

            if (!StringUtil.isNullOrEmpty(map.get("menuidlist")) && !StringUtil.isNullOrEmpty(map.get("roleid"))) {

                List<menurolerelevance> recordlst = new ArrayList<menurolerelevance>();

                for (String s : map.get("menuidlist").toString().split(",")) {

                    menurolerelevance menurolerelevance=new menurolerelevance();

                    menurolerelevance.setMenuid(Long.parseLong(s));
                    menurolerelevance.setRoleid(Long.parseLong(map.get("roleid").toString()));

                    recordlst.add(menurolerelevance);
                }

                if(recordlst.size()>0){
                    int result=this.menurolerelevanceService.Batchinsert(recordlst);

                    if(result>0){
                        return Response.status(Response.Status.OK).entity(new RestResult(RestResult.SUCCESS, "1", null)).build();
                    }else{
                        return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "2", null)).build();
                    }
                }else{
                    return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "2", null)).build();
                }


            } else {
                return Response.status(Response.Status.OK).entity(new RestResult(RestResult.FAILURE, "2", null)).build();
            }
        }

    }

    @Override
    public Response findlist(String param, HttpServletRequest request, HttpServletResponse response) {

        Map map = WebUtil.getParamsMap(param, "utf-8");

        BasePager pager = new BasePager();

        //region

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

        //endregion

        Criteria criteria = new Criteria();

        if (!StringUtil.isNullOrEmpty(map.get("roleid"))) {
            criteria.put("roleid", map.get("roleid").toString());
        }

        WebUtil.preparePageParams(request, pager, criteria, "A.createdDt desc");

        List<menurolerelevance> menurolerelevanceServiceList = this.menurolerelevanceService.selectByParams(criteria);

        int count=this.menurolerelevanceService.countByParams(criteria);

        Map<String, Object> data = new HashMap<String, Object>();

        data.put("total", count);
        data.put("rows", menurolerelevanceServiceList);

        String json = JsonUtil.toJson(data);

        return Response.status(Response.Status.OK).entity(json).build();
    }

    @Override
    public Response batchdelete(List<String> list) {

        int result = 0;

        result=this.menurolerelevanceService.batchdeleteByPrimaryKey(list);

        if (result > 0) {
            //删除成功
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null))).build();
        } else {
            //删除失败
            return Response.status(Response.Status.OK).entity(JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null))).build();
        }
    }
}
