package com.alfa.web.service.url;

import com.alfa.web.pojo.TotalUrlFilters;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/14.
 */
public interface UrlFilterService {

    /**
     * 根据条件查询记录集
     */
    List<TotalUrlFilters> selectByParams(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long Id);


    /**
     * 保存属性不为空的记录
     */
    int insertSelective(TotalUrlFilters record);

    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 批量删除Url
     * @param list
     * @return
     */
    int batchdeleteurl(List<String> list);
}
