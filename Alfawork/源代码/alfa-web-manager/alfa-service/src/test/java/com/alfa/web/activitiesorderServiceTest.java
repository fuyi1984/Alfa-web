package com.alfa.web;

import com.alfa.web.pojo.activitiesorder;
import com.alfa.web.pojo.beforesendmoney;
import com.alfa.web.service.money.activitiesorderService;
import com.alfa.web.service.money.beforesendmoneyService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/22.
 */
public class activitiesorderServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(activitiesorderServiceTest.class);

    @Autowired
    private activitiesorderService activitiesorderService;

    @Autowired
    private beforesendmoneyService beforesendmoneyService;

    @Test
    public void insert(){
        activitiesorder order=new activitiesorder();
        order.setOpenid("2");
        order.setActivitiesid(3l);
        order.setOrderid(3l);
        order.setIsfollow("0");
        this.activitiesorderService.insertSelective(order);
    }

    @Test
    public void search(){
        Criteria criteria=new Criteria();
        List<activitiesorder> list=this.activitiesorderService.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(list));
    }

    @Test
    public void update(){
        activitiesorder order=new activitiesorder();
        order.setId(1l);
        order.setOpenid("2");
        order.setActivitiesid(3l);
        order.setOrderid(3l);
        order.setIsfollow("0");
        this.activitiesorderService.updateByPrimaryKeySelective(order);
    }

    @Test
    public void delete(){
        List<String> list=new ArrayList<String>();
        list.add("1");
        this.activitiesorderService.batchdeleteByPrimaryKey(list);
    }

    @Test
    public void sendmoney(){

        List<String> list=new ArrayList<String>();
        list.add("4");

        //String param="idlist=4";

        Criteria criteria = new Criteria();

        List<beforesendmoney> beforesendmoneyList=this.beforesendmoneyService.selectByParams(criteria);

        if(beforesendmoneyList.size()==0) {

            //Map map = WebUtil.getParamsMap(param, "utf-8");

            String idlist= StringUtils.collectionToDelimitedString(list, ",");

            if (!StringUtil.isNullOrEmpty(idlist)) {
                criteria.put("idlist", idlist.split(","));
            }

            List<activitiesorder> activitiesorderlist = this.activitiesorderService.selectByParams(criteria);

            if (activitiesorderlist.size() > 0) {

                beforesendmoney money = new beforesendmoney();

                for (activitiesorder item : activitiesorderlist) {

                    money.setOpenid(item.getOpenid());
                    money.setActivitiesid(item.getActivitiesid());
                    money.setOrderid(item.getOrderid());
                    money.setOrderno(item.getOrderno());

                    this.beforesendmoneyService.insertSelective(money);
                }

                //发送成功
                System.out.println("1");
            }

            //没有发送的订单
            System.out.println("2");
        }else{
            //数据已经存在
            System.out.println("3");
        }
    }

    @Test
    public void testConvertListtoString(){
        List<String> list=new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(StringUtils.collectionToDelimitedString(list, ","));


    }
}
