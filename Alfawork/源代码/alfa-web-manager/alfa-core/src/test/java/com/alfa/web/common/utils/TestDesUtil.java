package com.alfa.web.common.utils;

import java.util.UUID;

import static com.alfa.web.common.utils.DesUtil.*;

/**
 * Created by Administrator on 2017/4/27.
 */
public class TestDesUtil extends TestBase {

    @Override
    public void testSpring() {
        String s = UUID.randomUUID().toString();
        s = s.replaceAll("-", "");
        String key = "loongrenders";
        try {
            System.out.println(s);
            String ss = encrypt(s, key);
            System.out.println(ss);
            ss = decrypt(ss, key);
            System.out.println(ss);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
