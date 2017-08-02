package com.alfa.web.dao;

import com.alfa.web.pojo.SysRelevance;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * 多对多关系集中映射.
 */
public interface SysRelevanceMapper {

    int deleteByParams(Criteria condition);

    int insertSelective(SysRelevance record);

    List<SysRelevance> selectByParams(Criteria condition);

    int updateByParamsSelective(SysRelevance record);
}
