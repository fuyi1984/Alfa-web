package com.alfa.web.service.sms;

import com.alfa.web.pojo.VwSmsStatus;
import com.alfa.web.pojo.orderSmsVw;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
public interface orderSmsVwService {
    /**
     * 根据条件查询记录集
     */
    List<orderSmsVw> selectByParams(Criteria example);
}
