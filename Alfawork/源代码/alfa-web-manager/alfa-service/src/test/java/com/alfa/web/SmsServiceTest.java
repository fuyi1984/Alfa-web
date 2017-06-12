package com.alfa.web;

import com.alfa.web.service.SmsService;
import com.alfa.web.service.SysUsersService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2017/6/12.
 */
public class SmsServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(SmsServiceTest.class);

    @Autowired
    private SmsService smsService;


}
