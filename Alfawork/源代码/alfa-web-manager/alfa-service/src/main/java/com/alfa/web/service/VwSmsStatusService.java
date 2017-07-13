package com.alfa.web.service;

import com.alfa.web.pojo.VwOrderStatus;
import com.alfa.web.pojo.VwSmsStatus;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/13.
 */
public interface VwSmsStatusService {
    /**
     * 根据条件查询记录集
     */
    List<VwSmsStatus> selectByParams(Criteria example);
}
