package com.alfa.web;

import com.alfa.web.pojo.*;
import com.alfa.web.service.money.*;
import com.alfa.web.util.*;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.vo.moneysendresultvo;
import com.alfa.web.vo.moneyvo;
import org.json.JSONException;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.DecimalFormat;
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

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private DecimalFormat df = new DecimalFormat("######0.00");

    private HttpClientUtil client = new HttpClientUtil();

    private Random rand = new Random();

    private Criteria criteria = new Criteria();

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
     * 发送失败后的红包订单
     */
    @Autowired
    private aftersendmoneyfailtureService aftersendmoneyfailtureService;


    /**
     * 参加了红包活动的订单
     */
    @Autowired
    private activitiesorderService activitiesorderService;


    /**
     * 红包发送
     */
    @Test
    public void MoneySend() throws IOException, JSONException {

        logger.info("MoneySenderStatus Start !!!");

        if (PropertiesUtil.getProperty("weixin.money.send.open").equals("true")) {

            //region

            Date dt = new Date();

            //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            //Random rand = new Random();

            //HttpClientUtil client = new HttpClientUtil();

            //DecimalFormat df = new DecimalFormat("######0.00");

            //Criteria criteria = new Criteria();

            /**
             * 清理查询条件
             */
            criteria.clear();

            /**
             * 开始时间
             */
            //criteria.put("createDtFrom", sdf.format(dt));
            /**
             * 结束时间
             */
            //criteria.put("createDtTo", sdf.format(dt));

            /**
             * 活动状态
             */
            criteria.put("statuslist", "0,1".split(","));


            //查询红包活动
            List<moneyactivities> activitieslist = this.moneyactivitiesServcie.selectByParams(criteria);

            /**
             * 活动启用
             */
            //criteria.put("status", "1");

            //查询红包活动
            //List<moneyactivities> activitieslist = this.moneyactivitiesServcie.selectByParams(criteria);


            for (moneyactivities money : activitieslist) {

                if (money.getStarttime().compareTo(dt)==-1&& money.getEndtime().compareTo(dt)==1) {

                    String Status=money.getStatus();

                    //活动启用
                    if (Status.equals("1")) {


                        //region 新人红包活动
                        if (money.getId().equals(4l)) {
                            PeopleMoneySend(money);
                        }

                    }
                    //活动停用
                    else if (Status.equals("0")) {
                        money.setStatus("1");
                        this.moneyactivitiesServcie.updateByPrimaryKeySelective(money);
                    }

                }else{
                    //活动强制停用
                    money.setStatus("2");
                    this.moneyactivitiesServcie.updateByPrimaryKeySelective(money);
                }

                //endregion
            }
            //endregion
        }

        logger.info("MoneySenderStatus End !!!");
    }


    /**
     * 红包发送的订单
     */
    @Test
    public void beforesendmoneyAdd() {

        beforesendmoney money = new beforesendmoney();

        for (int i = 0; i < 1; i++) {
            money.setOpenid("oZr2WwVrs0MqAGxPIzk1RNtuXsqE");
            money.setActivitiesid(4l);
            money.setOrderid(296l);
            money.setOrderno("SN20170925164519");
            this.beforesendmoneyService.insertSelective(money);
        }

    }

    @Test
    public void doubleformat() {
      /*  DecimalFormat df= new DecimalFormat("######0.00");
        double d1 = 3.23656;

        System.out.println(df.format(d1));*/

        System.out.println(WebUtil.decryptBase64(WebUtil.encryptBase64("1.06")));
    }

    /**
     * 新人红包发送
     *
     * @param money
     * @throws IOException
     */
    private void PeopleMoneySend(moneyactivities money) throws IOException {

        //Criteria criteria = new Criteria();

        //剩余红包数不能为0
        if (StringUtil.isNullOrEmpty(money.getRemainingnum()) || !money.getRemainingnum().equals("0")) {

            //region 新人红包活动

            //region 红包金额的计算

            //总金额
            //double totalmoney = Double.parseDouble(money.getMoney());

            //红包总数
            //int totalnum = Integer.parseInt(money.getTotalnum());

            //每个红包的最大金额数
            //double price = Double.valueOf(df.format(totalmoney / totalnum));

            double maxprice = Double.valueOf(df.format(Double.valueOf(money.getMaxprice())));
            double minprice = Double.valueOf(df.format(Double.valueOf(money.getMinprice())));

            //endregion

            //if (price >= 1.00 && price <= 200.00) {

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


                //Double randomprice = Double.valueOf(df.format(rand.nextDouble() * (price + 0.01 - 1.00) + 1.00));

                //System.out.println(randomprice);

                //mv.setNum(WebUtil.encryptBase64(String.valueOf(randomprice)));

                double randomprice = rand.nextDouble() * (maxprice + 0.01 - minprice) + minprice;

                mv.setNum(WebUtil.encryptBase64(df.format(randomprice)));

                //endregion


                //region

                System.out.println(JsonUtil.toJson(mv));
                //红包发送
                String result = client.connect(PropertiesUtil.getProperty("money.send.url"), "post", JsonUtil.toJson(mv));

                //获取红包的返回结果
                moneysendresultvo resultvo = JsonUtil.fromJson(result, moneysendresultvo.class);

                //region 返回结果后的处理流程

                if (resultvo.getCode() == 0) {
                    //region 红包发送成功

                    aftersendmoney aftersendmoney = new aftersendmoney();
                    aftersendmoney.setActivitiesid(money.getId());
                    aftersendmoney.setOpenid(item.getOpenid());
                    aftersendmoney.setOrderid(item.getOrderid());
                    aftersendmoney.setOrderno(item.getOrderno());
                    aftersendmoney.setMobile(item.getMobile());
                    aftersendmoney.setMoney(df.format(randomprice));

                    System.out.println(aftersendmoney.getMoney());

                    this.aftersendmoneyService.insertSelective(aftersendmoney);

                    idlist.add(String.valueOf(item.getId()));

                    if (StringUtil.isNullOrEmpty(money.getSendednum())) {
                        money.setSendednum("1");
                    } else {
                        money.setSendednum(String.valueOf(Integer.parseInt(money.getSendednum()) + 1));
                    }

                    money.setRemainingnum(String.valueOf(Integer.parseInt(money.getTotalnum()) - Integer.parseInt(money.getSendednum())));

                    this.moneyactivitiesServcie.updateByPrimaryKeySelective(money);

                    //endregion
                } else {
                    //region 红包发送失败

                    aftersendmoneyfailture aftersendmoneyfailture = new aftersendmoneyfailture();
                    aftersendmoneyfailture.setActivitiesid(money.getId());
                    aftersendmoneyfailture.setOpenid(item.getOpenid());
                    aftersendmoneyfailture.setOrderid(item.getOrderid());
                    aftersendmoneyfailture.setOrderno(item.getOrderno());
                    aftersendmoneyfailture.setMobile(item.getMobile());
                    aftersendmoneyfailture.setErrormessage(resultvo.getContent());

                    System.out.println(resultvo.getContent());

                    this.aftersendmoneyfailtureService.insertSelective(aftersendmoneyfailture);

                    idlist.add(String.valueOf(item.getId()));

                    //endregion
                }

                //endregion

                //region 修改关注红包活动的订单的状态

                criteria.put("openid", item.getOpenid());
                criteria.put("orderid", item.getOrderid());

                List<activitiesorder> activitiesorderlst = this.activitiesorderService.selectByParams(criteria);

                if (activitiesorderlst.size() > 0) {

                    activitiesorder aorder = activitiesorderlst.get(0);
                    aorder.setStatus(resultvo.getContent());
                    aorder.setVisible("-4");

                    this.activitiesorderService.updateByPrimaryKeySelective(aorder);
                }

                //endregion

                //endregion
            }

            if (idlist.size() > 0) {
                this.beforesendmoneyService.batchdeleteByPrimaryKey(idlist);
                idlist.clear();
            }

            //endregion

            //}

            //endregion
        }
    }

}
