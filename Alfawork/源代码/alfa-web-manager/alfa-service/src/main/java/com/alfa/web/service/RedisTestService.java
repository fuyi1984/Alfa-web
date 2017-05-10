package com.alfa.web.service;

/**
 * Created by Administrator on 2017/5/9.
 */
public interface RedisTestService {

    public String GetValue(String key);

    public void SetValue(String key,String value);
}
