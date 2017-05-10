package com.alfa.web.service.Impl;

import com.alfa.web.service.RedisTestService;
import com.alfa.web.util.Cache.JedisController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by Administrator on 2017/5/9.
 */
@Service
public class RedisTestServiceImpl implements RedisTestService{

    @Autowired
    JedisController jedisController;

    @Override
    public String GetValue(String key) {
        JedisPool jedisPool = null;
        Jedis jedis = null;

        jedisPool = jedisController.getJedisPool();
        jedis = jedisPool.getResource();

        return jedis.get(key);
    }

    @Override
    public void SetValue(String key,String value) {
        JedisPool jedisPool = null;
        Jedis jedis = null;

        jedisPool = jedisController.getJedisPool();
        jedis = jedisPool.getResource();

        jedis.set(key,value);
    }
}
