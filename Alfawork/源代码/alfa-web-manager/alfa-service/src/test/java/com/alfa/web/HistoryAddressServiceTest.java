package com.alfa.web;

import com.alfa.web.pojo.HistoryAddress;
import com.alfa.web.service.HistoryAddressService;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/7/10.
 */
public class HistoryAddressServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(HistoryAddressServiceTest.class);

    @Autowired
    private HistoryAddressService historyAddressService;

    @Test
    public void Add(){
        HistoryAddress historyAddress=new HistoryAddress();
        historyAddress.setIphone("18580043708");
        historyAddress.setCity("重庆市");
        historyAddress.setArea("沙坪坝区");
        historyAddress.setTownandstreets("陈家桥镇陈青路");
        WebUtil.prepareInsertParams(historyAddress);
        historyAddressService.insertSelective(historyAddress);
    }

    @Test
    public void Search(){
        Criteria criteria = new Criteria();
        List<HistoryAddress> historyAddressList=historyAddressService.selectByParams(criteria);
        for (HistoryAddress historyAddress:historyAddressList){

            String Province=StringUtil.isNullOrEmpty(historyAddress.getProvince())?"":historyAddress.getProvince();

            String City=StringUtil.isNullOrEmpty(historyAddress.getCity())?"":historyAddress.getCity();

            historyAddress.setFulladdress(Province+City);

            System.out.println(historyAddress.getFulladdress());
        }
    }
}
