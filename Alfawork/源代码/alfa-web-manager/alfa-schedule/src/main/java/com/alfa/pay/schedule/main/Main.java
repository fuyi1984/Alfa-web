package com.alfa.pay.schedule.main;


import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/5/5.
 */
public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException {
        new ClassPathXmlApplicationContext(
                new String[]{"classpath:application.xml",
                        "classpath:/config/spring/spring-common.xml",
                        "classpath:spring-schedule.xml"});
        // heartbeat
        while (true) {
            logger.debug("alfa-pay-schedule is alive.");
            Thread.sleep(60000);
        }


        //PythonInterpreter interpreter=new PythonInterpreter();
        //interpreter.execfile("d:\\my_util.py");
    }
}
