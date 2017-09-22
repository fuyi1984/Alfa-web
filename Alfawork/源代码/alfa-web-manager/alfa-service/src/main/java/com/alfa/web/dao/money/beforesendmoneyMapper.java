package com.alfa.web.dao.money;

import com.alfa.web.pojo.beforesendmoney;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * 红包发送前的准备
 */
public interface beforesendmoneyMapper {

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(beforesendmoney record);

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
    int updateByPrimaryKeySelective(beforesendmoney record);

    /**
     * 根据条件查询记录集
     */
    List<beforesendmoney> selectByParams(Criteria example);
}
