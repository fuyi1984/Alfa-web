package com.alfa.web;

import com.alfa.web.util.LicenseUtil;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2017/4/27.
 */
public class LicenseUtilTest extends TestBase {

    private static Logger logger = Logger.getLogger(LicenseUtilTest.class);

    @Test
    public void TestisDateValid() {
        try {
            boolean status = LicenseUtil.isDateValid();
            Assert.assertEquals(false, status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
