package com.alfa.web.job;

import com.alfa.web.pojo.aftersendmoney;
import com.alfa.web.pojo.beforesendmoney;
import com.alfa.web.pojo.moneyactivities;
import com.alfa.web.service.money.aftersendmoneyService;
import com.alfa.web.service.money.beforesendmoneyService;
import com.alfa.web.service.money.moneyactivitiesServcie;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 监控红包活动发送情况
 */
public class MoneySenderStatus {

    private final Logger logger = LoggerFactory.getLogger(MoneySenderStatus.class);

    /**
     * 红包活动
     */
    @Autowired
    private moneyactivitiesServcie moneyactivitiesServcie;

    /**
     * 发送前的红包订单
     */
    @Autowired
    private beforesendmoneyService beforesendmoneyService;

    /**
     * 发送成功后的红包订单
     */
    @Autowired
    private aftersendmoneyService aftersendmoneyService;

    /**
     * 红包发送
     */
    public void MoneySend(){
        logger.info("MoneySenderStatus Start !!!");

        if (PropertiesUtil.getProperty("weixin.money.send.open").equals("true")){

            //region

            Criteria criteria = new Criteria();

            List<moneyactivities> activitieslist=this.moneyactivitiesServcie.selectByParams(criteria);

            for(moneyactivities money:activitieslist){

                if(money.getId().equals(4l)){

                    //region 新人红包活动

                    criteria.clear();
                    criteria.put("activitiesid",money.getId());

                    List<beforesendmoney> beforesendmoneylist=this.beforesendmoneyService.selectByParams(criteria);

                    for(beforesendmoney item:beforesendmoneylist){

                        this.send();

                        aftersendmoney aftersendmoney=new aftersendmoney();
                        aftersendmoney.setActivitiesid(item.getActivitiesid());
                    }

                    //endregion
                }
            }
            //endregion
        }

        logger.info("MoneySenderStatus End !!!");
    }

    private void send(){
        System.out.println("send");
    }
}
