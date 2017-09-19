package com.alfa.web.service.money;

import com.alfa.web.pojo.moneyactivities;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18.
 */
public interface moneyactivitiesServcie {
    /**
     * 保存属性不为空的记录
     */
    int insertSelective(moneyactivities record);

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
    int updateByPrimaryKeySelective(moneyactivities record);

    /**
     * 根据条件查询记录集
     */
    List<moneyactivities> selectByParams(Criteria example);
}
