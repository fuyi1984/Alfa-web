package com.alfa.web;

import com.alfa.web.pojo.HistoryAddress;
import com.alfa.web.service.order.HistoryAddressService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.PropertiesUtil;
import com.alfa.web.util.StringUtil;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.util.pojo.RestResult;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;
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
        historyAddress.setProvince("重庆市");
        historyAddress.setCity("重庆市");
        historyAddress.setArea("沙坪坝区");
        historyAddress.setTownandstreets("陈家桥镇陈青路");
        historyAddress.setFulladdress("2222");

        historyAddress.setMobile("11111");
        historyAddress.setIphone("18580043708");
        WebUtil.prepareInsertParams(historyAddress);
        historyAddressService.insertSelective(historyAddress);
    }


    @Test
    public void insertHistoryAddress(){

        HistoryAddress historyAddress=new HistoryAddress();
        historyAddress.setProvince("重庆市");
        historyAddress.setCity("重庆市");
        historyAddress.setArea("沙坪坝区");
        historyAddress.setTownandstreets("陈家桥镇陈青路");
        historyAddress.setFulladdress("2222");

        historyAddress.setMobile("11111");
        historyAddress.setIphone("18580043708");
        WebUtil.prepareInsertParams(historyAddress);

        Criteria criteria = new Criteria();
        criteria.put("iphone", historyAddress.getIphone());

        int count=this.historyAddressService.countByParams(criteria);

        if(count<Integer.parseInt(PropertiesUtil.getProperty("currentUser.address.maxnum"))) {

            //region 小于规定的范围

            String city="",province="",area="",townandstreets="",fulladdress="";

            //region 查询条件

            if(!StringUtil.isNullOrEmpty(historyAddress.getCity())) {
                //市
                criteria.put("city", historyAddress.getCity());
                city=historyAddress.getCity();
            }

            if(!StringUtil.isNullOrEmpty(historyAddress.getProvince())) {
                //省
                criteria.put("province", historyAddress.getProvince());
                province=historyAddress.getProvince();
            }

            if(!StringUtil.isNullOrEmpty(historyAddress.getArea())) {
                //区
                criteria.put("area", historyAddress.getArea());
                area=historyAddress.getArea();
            }

            if(!StringUtil.isNullOrEmpty(historyAddress.getTownandstreets())) {
                //街道
                criteria.put("townandstreets", historyAddress.getTownandstreets());
                townandstreets=historyAddress.getTownandstreets();
            }

            if(!StringUtil.isNullOrEmpty(historyAddress.getFulladdress())) {
                //详细地址
                criteria.put("fulladdress", historyAddress.getFulladdress());
                fulladdress=historyAddress.getFulladdress();
            }

            List<HistoryAddress> list = this.historyAddressService.selectByParams(criteria);

            if (list.size() >= 1) {
                //收油地址已存在,无法再添加
                System.out.println("1");
            } else {

                historyAddress.setAddress(province+city+area+townandstreets+fulladdress);

                int result = this.historyAddressService.insertSelective(historyAddress);

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
        criteria.put("id","2");
        List<HistoryAddress> historyAddressList=historyAddressService.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(historyAddressList));
    }


    @Test
    public void deleteHistoryAddress(){
        HistoryAddress historyAddress=new HistoryAddress();
        historyAddress.setId(1L);
        historyAddressService.deleteByPrimaryKey(historyAddress.getId());
    }

    @Test
    public void update(){

        HistoryAddress record=new HistoryAddress();
        record.setId(9L);
        record.setProvince("重庆市");
        record.setCity("重庆市");
        record.setArea("沙坪坝区");
        record.setTownandstreets("陈家桥镇陈青路");
        record.setFulladdress("22223");

        record.setMobile("11111");
        record.setIphone("18580043708");

        String Json = "";

        String city="",province="",area="",townandstreets="",fulladdress="";

        //region 查询条件

        if(!StringUtil.isNullOrEmpty(record.getCity())) {
            //市
            city=record.getCity();
        }

        if(!StringUtil.isNullOrEmpty(record.getProvince())) {
            //省
            province=record.getProvince();
        }

        if(!StringUtil.isNullOrEmpty(record.getArea())) {
            //区
            area=record.getArea();
        }

        if(!StringUtil.isNullOrEmpty(record.getTownandstreets())) {
            //街道
            townandstreets=record.getTownandstreets();
        }

        if(!StringUtil.isNullOrEmpty(record.getFulladdress())) {
            //详细地址
            fulladdress=record.getFulladdress();
        }

        //endregion

        record.setAddress(province+city+area+townandstreets+fulladdress);

        WebUtil.prepareUpdateParams(record);

        int result = this.historyAddressService.updateByPrimaryKeySelective(record);

        if (result == 1) {
            //收油地址更新成功
            //Json = JsonUtil.toJson(new RestResult(RestResult.SUCCESS, "1", null));

            System.out.println("1");
        } else {
            //收油地址更新失败
            //Json = JsonUtil.toJson(new RestResult(RestResult.FAILURE, "2", null));

            System.out.println("2");
        }

    }
}
