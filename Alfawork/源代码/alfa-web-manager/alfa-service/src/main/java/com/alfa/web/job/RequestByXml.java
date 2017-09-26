package com.alfa.web.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2017/5/4.
 */
public class RequestByXml {

    private final Logger logger = LoggerFactory.getLogger(RequestByXml.class);

    public void runJob() {
        logger.debug("Quartz的任务调度！！！");
    }
}
