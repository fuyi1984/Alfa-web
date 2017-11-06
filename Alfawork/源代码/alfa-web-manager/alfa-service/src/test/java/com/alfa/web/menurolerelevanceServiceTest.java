package com.alfa.web;

import com.alfa.web.pojo.menurolerelevance;
import com.alfa.web.service.sys.menurolerelevanceService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.MenuUtil;
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

public class menurolerelevanceServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(menurolerelevanceServiceTest.class);

    @Autowired
    private menurolerelevanceService menurolerelevanceService;


    @Test
    public void selectParam() {

        Criteria criteria = new Criteria();
        criteria.put("roleid", "27");

        List<menurolerelevance> menurolerelevanceServiceList = this.menurolerelevanceService.selectByParams(criteria);

        System.out.println(JsonUtil.toJson(menurolerelevanceServiceList));

    }

    @Test
    public void findlist() {

        Criteria criteria = new Criteria();

        List<menurolerelevance> menurolerelevanceServiceList = this.menurolerelevanceService.selectByParams(criteria);

        List<Menus> MenusList = new ArrayList<Menus>();

        /*
        for (menurolerelevance item : menurolerelevanceServiceList) {

            if (item.getParentid().equals("0")) {

                //region  菜单目录

                Menus menus = new Menus();

                menus.setMenuId(Long.parseLong(item.getCascadeid()));
                menus.setMenuName(item.getMenuname());
                menus.setIcon(item.getIcon());
                menus.setUrl(item.getUrl());
                menus.setCreateTime(new Date());

                MenusList.add(menus);

                //endregion

            } else {

                //region 菜单

                for (Menus items : MenusList) {

                    if (items.getMenuId().equals(Long.parseLong(item.getParentid()))) {
                        //二级目录
                        Menus menus = new Menus();

                        menus.setMenuId(Long.parseLong(item.getCascadeid()));
                        menus.setMenuName(item.getMenuname());
                        menus.setIcon(item.getIcon());
                        menus.setUrl(item.getUrl());
                        menus.setCreateTime(new Date());

                        items.getMenuInfos().add(menus);

                    } else {

                        //region 二级目录
                        List<Menus> MenusListtemp = items.getMenuInfos();

                        //遍历二级目录
                        for (Menus m : MenusListtemp) {

                            List<Menus> x=m.getMenuInfos();

                            if(x.size()>0) {

                                while (true) {

                                    //遍历二级以后目录
                                    for (Menus n : x) {

                                        if (n.getMenuId().equals(Long.parseLong(item.getParentid()))) {

                                            Menus menus = new Menus();

                                            menus.setMenuId(Long.parseLong(item.getCascadeid()));
                                            menus.setMenuName(item.getMenuname());
                                            menus.setIcon(item.getIcon());
                                            menus.setUrl(item.getUrl());
                                            menus.setCreateTime(new Date());

                                            x.add(menus);

                                            break;
                                        }

                                    }

                                }
                            }

                        }

                        //endregion

                    }

                }
                //endregion
            }

        }
        */

        for (menurolerelevance item : menurolerelevanceServiceList) {
            Menus menus=new Menus();
            menus.setMenuId(Long.parseLong(item.getCascadeid()));
            menus.setParentId(Long.parseLong(item.getParentid()));
            menus.setMenuName(item.getMenuname());
            menus.setIcon(item.getIcon());
            menus.setUrl(item.getUrl());
            menus.setCreateTime(new Date());
            MenusList.add(menus);
        }

        String json = JsonUtil.toJson(MenuUtil.buildByRecursive(MenusList));

        System.out.println(json);
    }


}
