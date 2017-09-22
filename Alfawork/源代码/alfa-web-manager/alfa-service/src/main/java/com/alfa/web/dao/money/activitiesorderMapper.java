package com.alfa.web.dao.money;

import com.alfa.web.pojo.activitiesorder;
import com.alfa.web.pojo.moneyactivitiesconcern;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * 红包活动订单
 */
public interface activitiesorderMapper {

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(activitiesorder record);

    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 根据主键批量删除记录
     */
    int batchdeleteByPrimaryKey(List<String> list);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(activitiesorder record);

    /**
     * 根据条件查询记录集
     */
    List<activitiesorder> selectByParams(Criteria example);
}
