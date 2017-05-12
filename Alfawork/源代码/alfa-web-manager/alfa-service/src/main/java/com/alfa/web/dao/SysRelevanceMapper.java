package com.alfa.web.dao;

import com.alfa.web.pojo.SysRelevance;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * 多对多关系集中映射.
 */
public interface SysRelevanceMapper {

    /**
     * 根据条件删除记录
     * @param condition
     * @return
     */
    int deleteByParams(Criteria condition);

    /**
     * 保存属性不为空的记录
     * @param record
     * @return
     */
    int insertSelective(SysRelevance record);

    /**
     * 根据条件查询记录集
     * @param condition
     * @return
     */
    List<SysRelevance> selectByParams(Criteria condition);

    /**
     * 根据条件更新属性不为空的记录
     * @param record
     * @return
     */
    int updateByParamsSelective(SysRelevance record);

}
