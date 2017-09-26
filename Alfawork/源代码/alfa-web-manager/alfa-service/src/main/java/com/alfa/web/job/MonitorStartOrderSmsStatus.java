package com.alfa.web.job;

import com.alfa.web.pojo.orderSmsVw;
import com.alfa.web.pojo.vwSysUser;
import com.alfa.web.service.order.OrdersService;
import com.alfa.web.service.sms.SmsService;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * 提交订单后短信通知管理员分配任务
 */
public class MonitorStartOrderSmsStatus {

    private final Logger logger = LoggerFactory.getLogger(MonitorStartOrderSmsStatus.class);

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private com.alfa.web.service.sms.orderSmsVwService orderSmsVwService;

    @Autowired
    private com.alfa.web.service.sys.vwSysUserService vwSysUserService;

    @Autowired
    private SmsService smsService;

    //region 提交订单后发送通知短信

    public void runjob() throws UnsupportedEncodingException {

        logger.info("MonitorStartOrderSmsStatus Start !!!");

        if (PropertiesUtil.getProperty("order.sms.admin.open").equals("true")) {

            //region

            List<String> idlist = new ArrayList<String>();

            Criteria criteria = new Criteria();

            //region 获取刚提交的订单列表

            criteria.put("isSms", "0");

            //获取刚提交的订单列表
            List<orderSmsVw> orderSmsVwList = this.orderSmsVwService.selectByParams(criteria);

            //endregion


            if (orderSmsVwList.size() > 0) {

                //region 获取角色为系统管理员的用户列表

                criteria.clear();
                criteria.put("roleId", "27");
                List<vwSysUser> vwSysUsersList = this.vwSysUserService.selectByParams(criteria);

                //endregion

                if (vwSysUsersList.size() > 0) {

                    //region 短信通知

                    for (vwSysUser user : vwSysUsersList) {

                        String ret = this.smsService.sendSMS(user.getPhone(), String.format(PropertiesUtil.getProperty("notice.sms.admin"), orderSmsVwList.size()));

                        if (ret.equals("0")) {
                            logger.info("通知系统管理员的短信发送成功!");
                        } else {
                            logger.info("通知系统管理员的短信发送失败!");
                        }

                    }

                    for (orderSmsVw order : orderSmsVwList) {
                        idlist.add(String.valueOf(order.getOrderid()));
                    }

                    //endregion
                }
            }

            if (idlist.size() > 0) {

                //region 更新短信状态

                criteria.clear();

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

        logger.info("MonitorStartOrderSmsStatus End !!!");
    }

    //endregion

}
