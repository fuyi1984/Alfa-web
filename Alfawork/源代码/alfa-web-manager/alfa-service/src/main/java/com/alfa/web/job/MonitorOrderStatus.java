package com.alfa.web.job;

import com.alfa.web.pojo.VwOrderStatus;
import com.alfa.web.service.order.OrdersService;
import com.alfa.web.service.order.VwOrderStatusService;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/7/4.
 * 监控订单状态
 */
public class MonitorOrderStatus {

    private final Logger logger = LoggerFactory.getLogger(MonitorOrderStatus.class);

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private VwOrderStatusService vwOrderStatusService;

    //region 订单状态从确认自动变成完成

    public void FromConfirmToCompleted() throws ParseException {

        logger.info("MonitorOrderStatus Start !!!");

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = df.parse(df.format(new Date()));

        List<String> idlist = new ArrayList<String>();

        //region 查询满足条件的Web

        Criteria criteria = new Criteria();
        criteria.put("orgstatus", "3");

        List<VwOrderStatus> VwOrderStatusList = this.vwOrderStatusService.selectByParams(criteria);

        if (VwOrderStatusList.size() > 0) {

            for (VwOrderStatus item : VwOrderStatusList) {

                Date start = item.getUpdatedDt();

                long between = (now.getTime() - start.getTime()) / 1000;

                if (between > Long.parseLong(PropertiesUtil.getProperty("Order.Completed.MaxTime"))) {
                    idlist.add(String.valueOf(item.getOrderid()));
                }
            }
        }

        //endregion

        //region 自动更新订单状态

        if (idlist.size() > 0) {
            int result = this.ordersService.batchcompleteorderStatus(idlist);

            if(result>=1){
                logger.info("update success!");
            }else{
                logger.info("update failture!");
            }
        }
        //endregion

        logger.info("MonitorOrderStatus End !!!");

    }

    //endregion
}
