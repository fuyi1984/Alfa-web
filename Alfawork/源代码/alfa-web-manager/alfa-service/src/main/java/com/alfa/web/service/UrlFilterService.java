package com.alfa.web.service;

import com.alfa.web.pojo.UrlFilter;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
public interface UrlFilterService {

    /**
     * 根据条件查询记录集
     */
    List<UrlFilter> selectByParams(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long Id);


    /**
     * 保存属性不为空的记录
     */
    int insertSelective(UrlFilter record);

    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);
}
