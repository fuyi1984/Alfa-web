package com.alfa.web.util;

import com.alfa.web.common.pojo.Criteria;
import com.alfa.web.common.utils.ClassLoaderUtil;
import com.alfa.web.common.utils.SpringContextHolder;
import com.alfa.web.pojo.SysConfig;
import com.alfa.web.service.SysconfigService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
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

    // 调用方法将配置文件转化为类
    static {
        InputStreamReader reader = null;
        InputStream is = ClassLoaderUtil.getResourceAsStream("config/others/config.properties", PropertiesUtil.class);
        if (null != is) {
            try {
                reader = new InputStreamReader(is, "UTF-8");
                properties.load(reader);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取参数值
     *
     * @param key 参数名称
     * @return the property
     */
    public static String getProperty(String key) {
        String result = "";
        if (properties.containsKey(key)) {
            result = properties.getProperty(key);
        } else {
            // 配置文件中如果不存在, 则在系统配置表中查询
            Criteria criteria = new Criteria();
            criteria.put("configKey", key);
            List<SysConfig> configs = sysconfigService.selectByParams(criteria);
            if (configs != null && configs.size() > 0) {
                result = configs.get(0).getConfigValue();
            }
        }
        return result;
    }

    /**
     * @return the properties
     */
    public static Properties getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public static void setProperties(Properties properties) {
        PropertiesUtil.properties = properties;
    }

}
