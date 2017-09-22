package com.alfa.web.dao.money;

import com.alfa.web.pojo.aftersendmoney;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * 红包发送后的成功
 */
public interface aftersendmoneyMapper {

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(aftersendmoney record);

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
    int updateByPrimaryKeySelective(aftersendmoney record);

    /**
     * 根据条件查询记录集
     */
    List<aftersendmoney> selectByParams(Criteria example);
}
