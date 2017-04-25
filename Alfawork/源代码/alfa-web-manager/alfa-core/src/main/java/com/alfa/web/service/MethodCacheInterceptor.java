package com.alfa.web.service;

import net.sf.ehcache.Cache;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Administrator on 2017/4/24.
 */
public class MethodCacheInterceptor implements MethodInterceptor,
        InitializingBean {

    private static final Logger log = Logger.getLogger(MethodCacheInterceptor.class);

    private Cache cache;

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info(cache + "############MethodCacheInterceptor afterPropertiesSet cache############");
    }
}
