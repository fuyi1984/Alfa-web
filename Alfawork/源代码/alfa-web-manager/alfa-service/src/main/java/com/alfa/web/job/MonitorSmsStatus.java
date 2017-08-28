package com.alfa.web.job;

import com.alfa.web.pojo.VwSmsStatus;
import com.alfa.web.service.order.OrdersService;
import com.alfa.web.service.sms.SmsService;
import com.alfa.web.service.sms.VwSmsStatusService;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 监控订单通知短信的状态
 */
public class MonitorSmsStatus {

    private final Logger logger = LoggerFactory.getLogger(MonitorSmsStatus.class);

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private VwSmsStatusService vwSmsStatusService;

    @Autowired
    private SmsService smsService;


    //region 订单分配后发送通知短信

    public void runjob() throws UnsupportedEncodingException {

        logger.info("MonitorSmsStatus Start !!!");

        if (PropertiesUtil.getProperty("order.sms.transporter.open").equals("true")) {

            //region

            List<String> idlist = new ArrayList<String>();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Criteria criteria = new Criteria();
            criteria.put("isSms", "0");

            List<VwSmsStatus> vwSmsStatusList = this.vwSmsStatusService.selectByParams(criteria);

            if (vwSmsStatusList.size() > 0) {
                //region 短信通知
                if (vwSmsStatusList.size() > 3) {
                    for (int i = 0; i < 3; i++) {

                        String ret = this.smsService.sendSMS(vwSmsStatusList.get(i).getPhone(), String.format(PropertiesUtil.getProperty("notice.transporter"), vwSmsStatusList.get(i).getOrgname(), vwSmsStatusList.get(i).getIphone(),
                                sdf.format(vwSmsStatusList.get(i).getConfirmDt()), vwSmsStatusList.get(i).getOrderno()));

                        if (ret.equals("0")) {
                            logger.info("通知收运人员的短信发送成功!");
                        } else {
                            logger.info("通知收运人员的短信发送失败!");
                        }

                        idlist.add(String.valueOf(vwSmsStatusList.get(i).getOrderid()));

                    }
                } else {
                    for (VwSmsStatus item : vwSmsStatusList) {


                        String ret = this.smsService.sendSMS(item.getPhone(), String.format(PropertiesUtil.getProperty("notice.transporter"), item.getOrgname(), item.getIphone(), sdf.format(item.getConfirmDt()), item.getOrderno()));

                        if (ret.equals("0")) {
                            logger.info("通知收运人员的短信发送成功!");
                        } else {
                            logger.info("通知收运人员的短信发送失败!");
                        }

                        idlist.add(String.valueOf(item.getOrderid()));

                    }
                }

                //endregion
            }

            if (idlist.size() > 0) {

                //region 更新短信状态

                criteria.clear();

                criteria.put("confirmDt", sdf.format(new Date()));
                criteria.put("orderidlist", idlist.toArray());

                int result = this.ordersService.batchupdateSmsStatus(criteria);

                if (result >= 1) {
                    logger.info("update order success!!!");
                } else {
                    logger.info("update order failure!!!");
                }

                //endregion

            }

            //endregion
        }

        logger.info("MonitorSmsStatus End !!!");
    }

    //endregion

}


