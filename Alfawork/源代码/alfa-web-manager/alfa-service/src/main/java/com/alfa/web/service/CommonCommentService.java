package com.alfa.web.service;

import com.alfa.web.pojo.CommonComment;

import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/6.
 */
public interface CommonCommentService {

    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 根据条件删除记录
     */
    int deleteByParams(Criteria example);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    //int insert(HistoryAddress record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(CommonComment record);

    /**
     * 根据条件查询记录集
     */
    List<CommonComment> selectByParams(Criteria example);

    /**
     * 根据主键查询记录
     */
    CommonComment selectByPrimaryKey(Long id);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(CommonComment record);

    /**
     * 根据主键批量删除记录
     * @param list
     * @return
     */
    int batchdeleteByPrimaryKey(List<String> list);
}
