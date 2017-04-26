package com.alfa.web.util;

import com.alfa.web.common.utils.SpringContextHolder;
import com.alfa.web.service.SysconfigService;

import java.util.Properties;

/**
 * Created by Administrator on 2017/4/26.
 * 实例化config.properties方法
 */
public class PropertiesUtil {

    private static Properties properties = new Properties();

    /**
     * 系统配置Service
     */
    private static final SysconfigService sysconfigService = SpringContextHolder.getBean("sysconfigServiceImpl");


}
