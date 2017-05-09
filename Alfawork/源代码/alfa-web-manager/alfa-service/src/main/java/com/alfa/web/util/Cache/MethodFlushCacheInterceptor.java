package com.alfa.web.util.Cache;

import com.alfa.web.util.JsonUtil;
import net.sf.ehcache.Cache;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * Created by Administrator on 2017/5/8.
 */
public class MethodFlushCacheInterceptor implements MethodInterceptor, InitializingBean {

    private static final Logger log = Logger.getLogger(MethodFlushCacheInterceptor.class);

    private Cache cache;

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        String targetName = invocation.getMethod().getDeclaringClass().getName();
        String methodName = invocation.getMethod().getName();

        Object[] arguments = invocation.getArguments();
        Object result;

        for (Object key : cache.getKeys()) {
            if (String.valueOf(key).indexOf(targetName) != -1) {
                cache.remove(key);
            }
        }
        result = invocation.proceed();
        log.debug("############MethodFlushCacheInterceptor invoke targetName=" + targetName + " methodName=" + methodName + " arguments=" + JsonUtil.toJson(arguments) + "############");

        return result;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.debug(cache + "############MethodFlushCacheInterceptor afterPropertiesSet cache############");
    }
}
