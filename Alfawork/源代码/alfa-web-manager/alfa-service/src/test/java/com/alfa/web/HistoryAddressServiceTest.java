package com.alfa.web;

import com.alfa.web.pojo.HistoryAddress;
import com.alfa.web.service.order.HistoryAddressService;
import com.alfa.web.util.PropertiesUtil;
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
    public void insertHistoryAddress(){

        HistoryAddress record=new HistoryAddress();
        record.setIphone("13220356963");
        record.setAddress("4");
        WebUtil.prepareInsertParams(record);

        Criteria criteria = new Criteria();
        criteria.put("iphone", record.getIphone());

        int count=this.historyAddressService.countByParams(criteria);

        if(count<Integer.parseInt(PropertiesUtil.getProperty("currentUser.address.maxnum"))) {

            //region 小于规定的范围

            if (!StringUtil.isNullOrEmpty(record.getAddress())) {
                criteria.put("address", record.getAddress());
            }
            /*else {
                criteria.put("city", record.getCity());
                criteria.put("province", record.getProvince());
                criteria.put("area", record.getArea());
                criteria.put("townandstreets", record.getTownandstreets());
            }*/

            List<HistoryAddress> list = this.historyAddressService.selectByParams(criteria);

            if (list.size() >= 1) {
                //收油地址已存在,无法再添加
                System.out.println("1");
            } else {
                int result = this.historyAddressService.insertSelective(record);

                if (result >= 1) {
                    //收油地址插入成功
                    System.out.println("2");
                } else {
                    //收油地址插入失败
                    System.out.println("3");
                }
            }

            //endregion
        }else {
            //收油地址数据大于最大数量限制
            System.out.println("4");
        }
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


    @Test
    public void deleteHistoryAddress(){
        HistoryAddress historyAddress=new HistoryAddress();
        historyAddress.setId(1L);
        historyAddressService.deleteByPrimaryKey(historyAddress.getId());
    }
}
