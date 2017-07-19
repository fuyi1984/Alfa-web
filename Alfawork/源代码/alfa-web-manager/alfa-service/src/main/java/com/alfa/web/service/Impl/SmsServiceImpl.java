package com.alfa.web.service.Impl;

import com.alfa.web.service.SmsService;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.Sms.SDKHttpClient;
import com.alfa.web.vo.Mo;
import com.alfa.web.vo.StatusReport;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Administrator on 2017/6/11.
 */
@Service
public class SmsServiceImpl implements SmsService {

    private final Logger log = Logger.getLogger(this.getClass());

    /*public static String sn = "0SDK-EBB-6699-RGQNT";// 软件序列号,请通过亿美销售人员获取
    public static String key = "973664";// 序列号首次激活时自己设定
    public static String password = "973664";// 密码,请通过亿美销售人员获取
    public static String baseUrl = "http://hprpt2.eucp.b2m.cn:8080/sdkproxy/";*/

    public static String sn;// 软件序列号,请通过亿美销售人员获取
    public static String key;// 序列号首次激活时自己设定
    public static String password;// 密码,请通过亿美销售人员获取
    public static String baseUrl;

    /*public SmsServiceImpl() {
        sn=PropertiesUtil.getProperty("verify.sn");
        key=PropertiesUtil.getProperty("verify.key");
        password=PropertiesUtil.getProperty("verify.password");
        baseUrl=PropertiesUtil.getProperty("verify.baseUrl");
    }*/

    @Override
    public String registAndLogout() throws IOException {

        sn=PropertiesUtil.getProperty("verify.sn");
        key=PropertiesUtil.getProperty("verify.key");
        password=PropertiesUtil.getProperty("verify.password");
        baseUrl=PropertiesUtil.getProperty("verify.baseUrl");

        String url = baseUrl + "regist.action";
        String param = "cdkey=" + sn + "&password=" + password;
        String ret = SDKHttpClient.registAndLogout(url, param);
        System.out.println("注册结果:" + ret);
        return ret;
    }

    @Override
    public String getBalance() {

        sn=PropertiesUtil.getProperty("verify.sn");
        key=PropertiesUtil.getProperty("verify.key");
        password=PropertiesUtil.getProperty("verify.password");
        baseUrl=PropertiesUtil.getProperty("verify.baseUrl");

        String param = "cdkey=" + sn + "&password=" + key;
        String url = baseUrl + "querybalance.action";
        String balance = SDKHttpClient.getBalance(url, param);
        System.out.println("当前余额:" + balance);
        return balance;
    }

    @Override
    public String sendSMS(String mdn, String message) throws UnsupportedEncodingException {

        sn=PropertiesUtil.getProperty("verify.sn");
        key=PropertiesUtil.getProperty("verify.key");
        password=PropertiesUtil.getProperty("verify.password");
        baseUrl=PropertiesUtil.getProperty("verify.baseUrl");

        message = URLEncoder.encode(message, "UTF-8");
        String code = "888";
        long seqId = System.currentTimeMillis();
        String param = "cdkey=" + sn + "&password=" + key + "&phone=" + mdn + "&message=" + message + "&addserial=" + code + "&seqid=" + seqId;
        String url = baseUrl + "sendsms.action";
        String ret = SDKHttpClient.sendSMS(url, param);
        System.out.println("发送结果:" + ret);
        return ret;
    }

    @Override
    public int getMos() {

        sn=PropertiesUtil.getProperty("verify.sn");
        key=PropertiesUtil.getProperty("verify.key");
        password=PropertiesUtil.getProperty("verify.password");
        baseUrl=PropertiesUtil.getProperty("verify.baseUrl");

        String param = "cdkey=" + sn + "&password=" + key;
        String url = baseUrl + "getmo.action";
        List<Mo> mos = SDKHttpClient.getMos(url, sn, key);
        System.out.println("获取上行数量：" + mos.size());
        return mos.size();
    }

    @Override
    public int getReports() {

        sn=PropertiesUtil.getProperty("verify.sn");
        key=PropertiesUtil.getProperty("verify.key");
        password=PropertiesUtil.getProperty("verify.password");
        baseUrl=PropertiesUtil.getProperty("verify.baseUrl");

        String param = "cdkey=" + sn + "&password=" + key;
        String url = baseUrl + "getreport.action";
        List<StatusReport> srs = SDKHttpClient.getReports(url, sn, key);
        System.out.println("获取报告数量：" + srs.size());
        return srs.size();
    }

    @Override
    public String logout() {

        sn=PropertiesUtil.getProperty("verify.sn");
        key=PropertiesUtil.getProperty("verify.key");
        password=PropertiesUtil.getProperty("verify.password");
        baseUrl=PropertiesUtil.getProperty("verify.baseUrl");

        String url = baseUrl + "logout.action";
        String param = "cdkey=" + sn + "&password=" + password;
        String ret = SDKHttpClient.registAndLogout(url, param);
        System.out.println("注销结果:" + ret);
        return ret;
    }

    @Override
    public int sendMessage(String mobile, String message) throws IOException {
        return 0;
    }

    @Override
    public int sendMessage(List<String> mobile, String message) throws IOException {
        return 0;
    }

    @Override
    public int left() throws IOException {
        return 0;
    }
}
