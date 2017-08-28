package com.alfa.web;

import com.alfa.web.pojo.Orders;
import com.alfa.web.pojo.VwSmsStatus;
import com.alfa.web.service.order.OrdersService;
import com.alfa.web.service.sms.SmsService;
import com.alfa.web.service.sms.VwSmsStatusService;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/6/7.
 */
public class OrdersTest extends TestBase {

    private static Logger logger = Logger.getLogger(OrdersTest.class);

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private VwSmsStatusService vwSmsStatusService;

    @Autowired
    private SmsService smsService;

    @Test
    public void insertOrder(){
        Date dt = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Criteria criteria = new Criteria();
        criteria.put("iphone", "13062317530");
        criteria.put("createdDtLike",sdf.format(dt));

        String maxnum= PropertiesUtil.getProperty("orders.maxnum");

        int num = this.ordersService.countByParams(criteria);

        System.out.println("num:"+num+" maxnum:"+maxnum);

        Assert.assertEquals(true,num<=Integer.parseInt(PropertiesUtil.getProperty("orders.maxnum")));
    }

    @Test
    public void Add() throws Exception {

        Orders orders=new Orders();
        orders.setUsername("付益");
        orders.setIphone("15320295813");
        orders.setAddress("重庆市沙坪坝区陈家桥镇");
        orders.setNum("100");
        orders.setOrgname("阿尔发石油化工");
        orders.setOrgstatus("1");
        //orders.setWorkerid(18L);

        Assert.assertEquals(true,this.ordersService.insert(orders));
    }

    @Test
    public void Update(){
        Orders orders=new Orders();
        orders.setOrderid(3L);
        orders.setWorkerid(18L);

        WebUtil.prepareUpdateParams(orders);

        Assert.assertEquals(1,this.ordersService.updateByPrimaryKeySelective(orders));
    }

    @Test
    public void Delete(){
        Orders orders=new Orders();
        orders.setOrderid(3L);

        Assert.assertEquals(true,this.ordersService.delete(orders.getOrderid()));
    }

    @Test
    public void findOrder(){

    }

    @Test
    public void updateorderworker(){

        String param="idlist=12,13,14&workerid=3&orgstatus=2";

        Map map = WebUtil.getParamsMap(param, "utf-8");

        String[] idlist= ((String)map.get("idlist")).split(",");
        System.out.println(idlist);
        System.out.println(map.get("workerid"));
        System.out.println(map.get("orgstatus"));

    }

    @Test
    public void SendSms() throws UnsupportedEncodingException {
        logger.info("MonitorSmsStatus Start !!!");

        List<String> idlist = new ArrayList<String>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Criteria criteria = new Criteria();
        criteria.put("isSms", "0");

        List<VwSmsStatus> vwSmsStatusList = this.vwSmsStatusService.selectByParams(criteria);

        if (vwSmsStatusList.size() > 0) {
            //region 短信通知
            if (vwSmsStatusList.size() > 3) {
                for (int i = 0; i < 3; i++) {
                    if (PropertiesUtil.getProperty("sms.open").equals("true")) {

                        /*String ret = this.smsService.sendSMS(vwSmsStatusList.get(i).getPhone(), String.format(PropertiesUtil.getProperty("notice.transporter"),vwSmsStatusList.get(i).getOrgname(),vwSmsStatusList.get(i).getIphone(),vwSmsStatusList.get(i).getConfirmDt(),vwSmsStatusList.get(i).getOrderno()));

                        if (ret == "0") {
                            logger.info("通知收运人员的短信发送成功!");
                        } else {
                            logger.info("通知收运人员的短信发送失败!");
                        }
*/
                        idlist.add(String.valueOf(vwSmsStatusList.get(i).getOrderid()));
                    }
                }
            } else {
                for (VwSmsStatus item : vwSmsStatusList) {
                    if (PropertiesUtil.getProperty("sms.open").equals("true")) {

                        /*String ret = this.smsService.sendSMS(item.getPhone(), String.format(PropertiesUtil.getProperty("notice.transporter"),item.getOrgname(),item.getIphone(),item.getConfirmDt(),item.getOrderno()));

                        if (ret == "0") {
                            logger.info("通知收运人员的短信发送成功!");
                        } else {
                            logger.info("通知收运人员的短信发送失败!");
                        }*/

                        idlist.add(String.valueOf(item.getOrderid()));
                    }
                }
            }

            //endregion
        }

        if (idlist.size() > 0) {

            //region 更新短信状态

            criteria.clear();

            criteria.put("confirmDt", sdf.format(new Date()));
            criteria.put("orderidlist",idlist.toArray());

            int result=this.ordersService.batchupdateSmsStatus(criteria);

            if(result>=1){
                logger.info("update order success!!!");
            }else{
                logger.info("update order failure!!!");
            }

            //endregion

        }

        logger.info("MonitorSmsStatus End !!!");
    }

    @Test
    public void testSendSms() throws UnsupportedEncodingException {

        String message=String.format(PropertiesUtil.getProperty("notice.transporter"),"阿尔发石油","15320295813",(new Date()).toString(),"SN123");

        String ret = this.smsService.sendSMS("15320295813",message);

        //String ret="0";

        if (ret.equals("0")) {
            logger.info("通知收运人员的短信发送成功!");
        } else {
            logger.info("通知收运人员的短信发送失败!");
        }
    }

    @Test
    public void countByParams(){
        Criteria criteria = new Criteria();

        criteria.put("phone","18580043708");
        criteria.put("roleId","9");
        criteria.put("orgstatus","4");

        int count = this.ordersService.countByParams(criteria);

        System.out.println(count);
    }


}
