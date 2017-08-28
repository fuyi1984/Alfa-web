package com.alfa.web;

import com.alfa.web.pojo.VerifyCode;
import com.alfa.web.service.sms.VerifyCodeService;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.constant.WebConstants;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/14.
 */
public class VerifyCodeTest extends TestBase {

    private static Logger logger = Logger.getLogger(VerifyCodeTest.class);

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Test
    public void insertVerifyCodeAndReturn() {
        String mobile = "18580043708";

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

    @Test
    public void judgeValidtime() throws ParseException {
        Criteria criteria = new Criteria();

        criteria.put("code", "869689");
        criteria.put("boundAccount", "18580043708");
        criteria.put("type", WebConstants.VerifyCode.type0);

        List<VerifyCode> vcList = this.verifyCodeService.selectByParams(criteria);

        if (vcList.size() == 0) {
            System.out.println("no data");
        } else {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date now = df.parse(df.format(new Date()));

            VerifyCode code = vcList.get(0);
            Date start = code.getCreatedDt();

            long between = (now.getTime() - start.getTime()) / 1000;

            if (between > Long.parseLong(PropertiesUtil.getProperty("sms.verify.Valid.time"))) {
                System.out.println("failture");
            } else {
                System.out.println("success");
            }
        }
    }
}
