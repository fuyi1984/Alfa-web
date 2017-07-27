package com.alfa.web.service;

import com.alfa.web.pojo.fileinfo;

/**
 * Created by Administrator on 2017/7/26.
 */
public interface fileinfoService {

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(fileinfo record);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(fileinfo record);
}
