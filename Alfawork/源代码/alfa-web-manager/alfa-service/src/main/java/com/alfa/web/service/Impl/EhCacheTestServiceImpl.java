package com.alfa.web.service.Impl;

import com.alfa.web.service.EhCacheTestService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/5/8.
 */
@Service
public class EhCacheTestServiceImpl implements EhCacheTestService {

    @Cacheable(value="myEhcache",key="#param")
    @Override
    public String getTimestamp(String param) {
        Long timestamp = System.currentTimeMillis();
        return timestamp.toString();
    }
}
