package com.alfa.web;

import com.alfa.web.pojo.Vwweixinheadfile;
import com.alfa.web.service.sys.userregisterbehaviorService;
import com.alfa.web.service.weixin.VwweixinheadfileService;
import com.alfa.web.util.JsonUtil;
import com.alfa.web.util.pojo.Criteria;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */
public class VwweixinheadfileServiceTest extends TestBase {

    private static Logger logger = Logger.getLogger(VwweixinheadfileServiceTest.class);

    @Autowired
    private VwweixinheadfileService vwweixinheadfileService;

    @Test
    public void selectParams(){
        Criteria criteria = new Criteria();
        List<Vwweixinheadfile> list=this.vwweixinheadfileService.selectByParams(criteria);
        System.out.println(JsonUtil.toJson(list));
    }
}
