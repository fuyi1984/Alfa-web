package com.alfa.web;

import com.alfa.web.pojo.VerifyCode;
import com.alfa.web.service.SmsService;
import com.alfa.web.service.VerifyCodeService;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
public class VerifyCodeTest extends TestBase {

    private static Logger logger = Logger.getLogger(VerifyCodeTest.class);

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Test
    public void insertVerifyCodeAndReturn()
    {
        String mobile="18580043708";

        Map<String, String> result = null;

        try {
            result = new HashMap<String, String>();
            VerifyCode vc = new VerifyCode();
            vc.setType(WebConstants.VerifyCode.type0);
            vc.setBoundAccount(mobile);
            String code = verifyCodeService.insertVerifyCodeAndReturn(vc);
            result.put("captcha", code);
            logger.debug(mobile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
