package com.alfa.web;

import com.alfa.web.service.sms.SmsService;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.WebUtil;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Administrator on 2017/6/12.
 */
public class SmsServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(SmsServiceTest.class);

    @Autowired
    private SmsService smsService;

    @Test
    public void registAndLogout() throws IOException {
       String result=smsService.registAndLogout();
       System.out.println(result);
    }

    @Test
    public void sendSMS() throws UnsupportedEncodingException {
        String result=smsService.sendSMS("15320295813", PropertiesUtil.getProperty("verify.content") + WebUtil.randomCaptcha(6));
        System.out.println(result);
    }

    @Test
    public void insertVerifyCodeAndReturn(){

    }
}
