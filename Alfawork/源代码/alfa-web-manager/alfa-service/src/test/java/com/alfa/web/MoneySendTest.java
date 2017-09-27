package com.alfa.web;

import com.alfa.web.job.MoneySenderStatus;
import com.alfa.web.pojo.aftersendmoney;
import com.alfa.web.pojo.beforesendmoney;
import com.alfa.web.pojo.moneyactivities;
import com.alfa.web.service.money.aftersendmoneyService;
import com.alfa.web.service.money.beforesendmoneyService;
import com.alfa.web.service.money.moneyactivitiesServcie;
import com.alfa.web.util.HttpClientUtil;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.vo.moneysendresultvo;
import com.alfa.web.vo.moneyvo;
import org.json.JSONException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/9/27.
 */
public class MoneySendTest extends TestBase {

    private static Logger logger = LoggerFactory.getLogger(MoneySendTest.class);

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
    @Test
    public void MoneySend() throws IOException, JSONException {

        logger.info("MoneySenderStatus Start !!!");

        if (PropertiesUtil.getProperty("weixin.money.send.open").equals("true")) {

            //region

            Date dt = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            Random rand = new Random();
            HttpClientUtil client=new HttpClientUtil();

            Criteria criteria = new Criteria();
            /**
             * 开始时间
             */
            criteria.put("createDtFrom", sdf.format(dt));
            /**
             * 结束时间
             */
            criteria.put("createDtTo", sdf.format(dt));

            //查询红包活动
            List<moneyactivities> activitieslist = this.moneyactivitiesServcie.selectByParams(criteria);


            for (moneyactivities money : activitieslist) {

                if (money.getId().equals(4l)) {

                    int moneysum=0;

                    //活动启用
                    if (money.getStatus().equals("1")) {

                        //region 新人红包活动

                        //region 红包金额的计算

                        //总金额
                        double totalmoney = Double.parseDouble(money.getMoney());

                        //红包总数
                        int totalnum = Integer.parseInt(money.getTotalnum());

                        //每个红包的最大金额数
                        double price = totalmoney / totalnum;

                        //endregion

                        if (price >= 1.00) {

                            //region 查询需要发红包的订单

                            criteria.clear();
                            criteria.put("activitiesid", money.getId());

                            List<beforesendmoney> beforesendmoneylist = this.beforesendmoneyService.selectByParams(criteria);

                            //endregion

                            //region 发送红包

                            List<String> idlist = new ArrayList<String>();

                            for (beforesendmoney item : beforesendmoneylist) {

                                //region

                                moneyvo mv = new moneyvo();
                                //openid
                                mv.setOpenid(WebUtil.encryptBase64(item.getOpenid()));
                                //商户号
                                mv.setKey(WebUtil.encryptBase64(PropertiesUtil.getProperty("weixin.money.send.bussinessnum")));
                                //密钥
                                mv.setPaykey(WebUtil.encryptBase64(PropertiesUtil.getProperty("weixin.money.send.key")));

                                mv.setNum(WebUtil.encryptBase64(String.valueOf(rand.nextDouble()*(price+0.01-1.00)+1.00)));

                                //endregion


                                //region

                                String result=client.connect(PropertiesUtil.getProperty("money.send.url"), "post", JsonUtil.toJson(mv));

                                moneysendresultvo resultvo=JsonUtil.fromJson(result,moneysendresultvo.class);

                                if(resultvo.getCode()==0) {

                                    /**
                                     * 红包发送成功
                                     */

                                    aftersendmoney aftersendmoney = new aftersendmoney();
                                    aftersendmoney.setActivitiesid(item.getActivitiesid());
                                    aftersendmoney.setOpenid(item.getOpenid());
                                    aftersendmoney.setOrderid(item.getOrderid());
                                    aftersendmoney.setOrderno(item.getOrderno());
                                    aftersendmoney.setMoney(mv.getNum());

                                    this.aftersendmoneyService.insertSelective(aftersendmoney);

                                    idlist.add(String.valueOf(item.getId()));

                                    moneysum+=1;

                                    money.setSendednum(String.valueOf(moneysum));
                                    money.setRemainingnum(String.valueOf(Integer.parseInt(money.getTotalnum())-moneysum));
                                    this.moneyactivitiesServcie.updateByPrimaryKeySelective(money);
                                }

                                //endregion
                            }

                            if (idlist.size() > 0) {
                                this.beforesendmoneyService.batchdeleteByPrimaryKey(idlist);
                                idlist.clear();
                            }

                            //endregion

                        }

                        //endregion
                    }
                }
            }
            //endregion
        }

        logger.info("MoneySenderStatus End !!!");
    }

}
