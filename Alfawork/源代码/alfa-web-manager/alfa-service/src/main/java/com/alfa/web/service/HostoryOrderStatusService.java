package com.alfa.web.service;

import com.alfa.web.pojo.HostoryOrderStatus;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */
public interface HostoryOrderStatusService {
    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(HostoryOrderStatus record);

    /**
     * 根据条件查询记录集
     */
    List<HostoryOrderStatus> selectByParams(Criteria example);
}
