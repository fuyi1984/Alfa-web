package com.alfa.web.job;

import com.alfa.web.service.money.moneyactivitiesServcie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 监控红包活动发送情况
 */
public class MoneySenderStatus {

    private final Logger logger = LoggerFactory.getLogger(MoneySenderStatus.class);

    @Autowired
    private moneyactivitiesServcie moneyactivitiesServcie;

    /**
     * 红包发送
     */
    public void MoneySend(){
        logger.info("MoneySenderStatus Start !!!");


        logger.info("MoneySenderStatus End !!!");
    }
}
