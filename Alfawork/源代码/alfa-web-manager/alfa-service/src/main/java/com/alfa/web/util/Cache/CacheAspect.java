package com.alfa.web.util.Cache;

import net.sf.ehcache.Cache;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

/**
 * Created by Administrator on 2017/5/8.
 */
public class CacheAspect {
    private static final Logger log = Logger.getLogger(MethodCacheInterceptor.class);

    private Cache cache;

    public void setCache(Cache cache) {
        this.cache = cache;
    }


    public void doCache(JoinPoint jp) {
        log.info("--------------------set cache---------------------");
    }
}
