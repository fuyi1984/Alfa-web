package com.alfa.web.service.news;

import com.alfa.web.pojo.newstop;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * 新闻头条
 */
public interface newstopService {

    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 根据主键批量删除记录
     */
    int batchdeleteByPrimaryKey(List<String> list);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(newstop record);

    /**
     * 根据条件查询记录集
     */
    List<newstop> selectByParams(Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(newstop record);
}
