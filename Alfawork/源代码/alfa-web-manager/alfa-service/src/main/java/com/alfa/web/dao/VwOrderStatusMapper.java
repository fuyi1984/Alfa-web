package com.alfa.web.dao;

import com.alfa.web.pojo.VwOrderStatus;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */
public interface VwOrderStatusMapper {
    /**
     * 根据条件查询记录集
     */
    List<VwOrderStatus> selectByParams(Criteria example);
}
