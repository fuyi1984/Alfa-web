package com.alfa.web;

import com.alfa.web.service.common.RedisTestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/9.
 */
public class RedisTestServiceTest extends TestBase {

    @Autowired
    private RedisTestService redisTestService;

    @Test
    public void GetValue(){
        System.out.println(redisTestService.GetValue("Test"));
    }

    @Test
    public void SetValue(){
        redisTestService.SetValue("Test","11111");
    }
}
