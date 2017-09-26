package com.alfa.web;

import com.alfa.web.service.common.EhCacheTestService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/5/8.
 */
public class EhCacheTestServiceTest extends TestBase {

    @Autowired
    private EhCacheTestService ehCacheTestService;

    @Test
    public void getTimestampTest() throws InterruptedException{
        System.out.println("第一次调用：" + ehCacheTestService.getTimestamp("param"));
        Thread.sleep(2000);
        System.out.println("2秒之后调用：" + ehCacheTestService.getTimestamp("param"));
        Thread.sleep(11000);
        System.out.println("再过11秒之后调用：" + ehCacheTestService.getTimestamp("param"));
    }
}
