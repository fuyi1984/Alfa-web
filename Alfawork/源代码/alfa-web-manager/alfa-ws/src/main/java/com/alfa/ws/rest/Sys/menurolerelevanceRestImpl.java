package com.alfa.ws.rest.Sys;

import com.alfa.web.pojo.menurolerelevance;
import com.alfa.web.service.sys.EMenuInfosService;
import com.alfa.web.service.sys.menurolerelevanceService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.vo.MenuInfo;
import com.alfa.web.vo.Menus;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public Response findist(String param, HttpServletRequest request, HttpServletResponse response) {

        Map map= WebUtil.getParamsMap(param,"utf-8");

        BasePager pager=new BasePager();

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
            criteria.put("roleid",  map.get("roleid").toString());
        }

        WebUtil.preparePageParams(request, pager, criteria, "B.parentid");

        List<menurolerelevance> menurolerelevanceServiceList=this.menurolerelevanceService.selectByParams(criteria);

        List<Menus> MenusList=new ArrayList<Menus>();

        Menus menus=new Menus();

        for(menurolerelevance item:menurolerelevanceServiceList){

            if(item.getParentid().equals("0")){

                //region  菜单目录

                menus.setMenuId(Long.parseLong(item.getCascadeid()));
                menus.setMenuName(item.getMenuname());
                menus.setIcon(item.getIcon());
                menus.setUrl(item.getUrl());

                MenusList.add(menus);

                //endregion

            }else{

                //region 菜单

                for(Menus items:MenusList){
                    if(items.getMenuId().equals(item.getParentid())){

                        MenuInfo menuInfo=new MenuInfo();
                        menuInfo.setMenuId(Long.parseLong(item.getCascadeid()));
                        menuInfo.setMenuName(item.getMenuname());
                        menuInfo.setIcon(item.getIcon());
                        menuInfo.setUrl(item.getUrl());

                        menus.getMenuInfos().add(menuInfo);
                    }
                }
                //endregion
            }
        }

        String json = JsonUtil.toJson(MenusList);

        return Response.status(Response.Status.OK).entity(json).build();
    }
}
