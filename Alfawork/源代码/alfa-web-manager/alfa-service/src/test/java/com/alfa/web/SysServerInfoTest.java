package com.alfa.web;

import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.Properties;

/**
 * Created by Administrator on 2017/6/4.
 */
public class SysServerInfoTest extends TestBase {
    private static Logger logger = Logger.getLogger(SysServerInfoTest.class);

    @Test
    public void PrintInfo(){
        Properties props=System.getProperties();
        System.out.println("Java的运行环境版本："+props.getProperty("java.version"));
        System.out.println("操作系统的名称："+props.getProperty("os.name"));
        System.out.println(props.getProperty("os.name"));
    }
}
