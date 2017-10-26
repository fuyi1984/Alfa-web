package com.alfa.web;

import com.alfa.web.pojo.moneyactivities;
import com.alfa.web.pojo.moneyactivitiesconcern;
import com.alfa.web.pojo.td_weixin_users;
import com.alfa.web.service.money.moneyactivitiesServcie;
import com.alfa.web.service.money.moneyactivitiesconcernServcie;
import com.alfa.web.service.weixin.weixin_usersService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/9/20.
 */
public class moneyactivitiesServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(moneyactivitiesServiceTest.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    SimpleDateFormat fullsdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private moneyactivitiesServcie moneyactivitiesService;

    @Autowired
    private moneyactivitiesconcernServcie moneyactivitiesconcernService;

    @Autowired
    private com.alfa.web.service.weixin.weixin_usersService weixin_usersService;

    @Test
    public void insert() throws ParseException {

        moneyactivities money=new moneyactivities();
        money.setTitle("4411");
        money.setContent("442222");

        money.setStarttime(new Date());
        money.setEndtime(new Date());

        System.out.println(sdf.format(money.getStarttime()));
        System.out.println(sdf.format(money.getStarttime()));

        String starttime=sdf.format(money.getStarttime())+" 00:00:00";
        String endtime=sdf.format(money.getStarttime())+" 23:59:59";

        money.setStarttime(fullsdf.parse(starttime));
        money.setEndtime(fullsdf.parse(endtime));

        this.moneyactivitiesService.insertSelective(money);
    }

    @Test
    public void search(){
        Criteria criteria = new Criteria();
        /**
         * 开始时间
         */
        criteria.put("createDtFrom", "2017-10-17 00:00:00");
        /**
         * 结束时间
         */
        criteria.put("createDtTo", "2017-10-17 23:59:59");

        List<moneyactivities> list=this.moneyactivitiesService.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(list));
    }

    @Test
    public void delete(){
        List<String> list=new ArrayList<String>();
        list.add("1");
        this.moneyactivitiesService.batchdeleteByPrimaryKey(list);
    }

    @Test
    public void update(){
        moneyactivities money=new moneyactivities();
        money.setId(2L);
        money.setTitle("4411");
        money.setContent("442222");
        money.setMoney("11111");
        this.moneyactivitiesService.updateByPrimaryKeySelective(money);
    }

    @Test
    public void isNotGetRedMoeny(){

        moneyactivitiesconcern money=new moneyactivitiesconcern();
        money.setOpenid("onsayw7EeBfqssiVK13Otl5OuvsA");
        money.setActivitiesid(4l);

        Criteria criteria = new Criteria();

        Date dt = new Date();

        String phonelist = PropertiesUtil.getProperty("money.send.phonelist");

        if (StringUtil.isNullOrEmpty(phonelist)) {

            //region phonelist为空

            criteria.clear();

            criteria.put("id", money.getActivitiesid());

            //显示状态
            criteria.put("isvisible", "4");

            List<moneyactivities> moneyactivitieslist = this.moneyactivitiesService.selectByParams(criteria);

            if (moneyactivitieslist.size() > 0) {

                moneyactivities activities = moneyactivitieslist.get(0);

                if (activities.getStarttime().compareTo(dt) == -1 && activities.getEndtime().compareTo(dt) == 1) {

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

                                System.out.println("用户已经关注过红包活动");
                            } else {
                                System.out.println("success");
                            }
                        }
                        //活动的红包总数已经领完
                        else {
                            System.out.println("活动的红包总数已经领完");
                        }

                        //endregion
                    }
                    //活动手动停用
                    else if (activities.getStatus().equals("2")) {
                        System.out.println("活动手动停用");
                    }
                    //活动停用
                    else {
                        System.out.println("活动停用");
                    }

                    //endregion
                } else {
                    //region 时间有效期范围外
                    System.out.println("时间有效期范围外");
                    //endregion
                }

            } else {
                //活动不存在
                System.out.println("活动不存在");
            }

            //endregion

        } else {

            //region phonelist不为空

            String[] phonelists = phonelist.split(",");

            criteria.clear();

            criteria.put("openid", money.getOpenid());

            List<td_weixin_users> td_weixin_userslist = this.weixin_usersService.selectByParams(criteria);


            if (td_weixin_userslist.size() > 0) {

                td_weixin_users users = td_weixin_userslist.get(0);

                if (!StringUtil.isNullOrEmpty(users.getMobile()) && !StringUtil.isNullOrEmpty(users.getMobiletoken())) {

                    for (String s : phonelists) {
                        if (s.equals(users.getMobile())) {
                            //region 手机号匹配

                            criteria.clear();

                            criteria.put("id", money.getActivitiesid());

                            //显示状态
                            criteria.put("isvisible", "4");

                            List<moneyactivities> moneyactivitieslist = this.moneyactivitiesService.selectByParams(criteria);

                            if (moneyactivitieslist.size() > 0) {

                                moneyactivities activities = moneyactivitieslist.get(0);

                                if (activities.getStarttime().compareTo(dt) == -1 && activities.getEndtime().compareTo(dt) == 1) {

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

                                                System.out.println("用户已经关注过红包活动");
                                            } else {
                                                System.out.println("success");
                                            }
                                        }
                                        //活动的红包总数已经领完
                                        else {
                                            System.out.println("活动的红包总数已经领完");
                                        }

                                        //endregion
                                    }
                                    //活动手动停用
                                    else if (activities.getStatus().equals("2")) {
                                        System.out.println("活动手动停用");
                                    }
                                    //活动停用
                                    else {
                                        System.out.println("活动停用");
                                    }

                                    //endregion
                                } else {
                                    //region 时间有效期范围外
                                    System.out.println("时间有效期范围外");
                                    //endregion
                                }

                            } else {
                                //活动不存在
                                System.out.println("活动不存在");
                            }

                            //endregion
                        }
                    }

                    //手机号不匹配
                    System.out.println("手机号不匹配");

                } else {
                    //手机号不能为空
                    System.out.println("手机号不能为空");
                }

            } else {
                //openid不存在
                System.out.println("openid不存在");
            }

            //endregion

        }
    }
}
