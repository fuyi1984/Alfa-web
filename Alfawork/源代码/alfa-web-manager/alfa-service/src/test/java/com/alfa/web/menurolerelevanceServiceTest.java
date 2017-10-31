package com.alfa.web;

import com.alfa.web.pojo.menurolerelevance;
import com.alfa.web.service.sys.menurolerelevanceService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.BasePager;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.vo.MenuInfo;
import com.alfa.web.vo.Menus;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class menurolerelevanceServiceTest extends TestBase{

    private static Logger logger = Logger.getLogger(menurolerelevanceServiceTest.class);

    @Autowired
    private menurolerelevanceService menurolerelevanceService;


    @Test
    public void selectParam(){

        Criteria criteria=new Criteria();

        List<menurolerelevance> menurolerelevanceServiceList=this.menurolerelevanceService.selectByParams(criteria);

        System.out.println(JsonUtil.toJson(menurolerelevanceServiceList));

    }

    @Test
    public void findlist(){

        Criteria criteria = new Criteria();

        List<menurolerelevance> menurolerelevanceServiceList=this.menurolerelevanceService.selectByParams(criteria);

        List<Menus> MenusList=new ArrayList<Menus>();



        for(menurolerelevance item:menurolerelevanceServiceList){

            if(item.getParentid().equals("0")){

                //region  菜单目录

                Menus menus=new Menus();
                menus.setMenuId(Long.parseLong(item.getCascadeid()));
                menus.setMenuName(item.getMenuname());
                menus.setIcon(item.getIcon());

                MenusList.add(menus);

                //endregion

            }else{

                //region 菜单

                for(Menus items:MenusList){
                    if(items.getMenuId().equals(Long.parseLong(item.getParentid()))){

                        MenuInfo menuInfo=new MenuInfo();
                        menuInfo.setMenuId(Long.parseLong(item.getCascadeid()));
                        menuInfo.setMenuNames(item.getMenuname());
                        menuInfo.setIcon(item.getIcon());
                        menuInfo.setUrl(item.getUrl());
                        menuInfo.setCreateTime(new Date());

                        items.getMenuInfos().add(menuInfo);
                    }
                }
                //endregion
            }
        }

        String json = JsonUtil.toJson(MenusList);

        System.out.println(json);
    }
}
