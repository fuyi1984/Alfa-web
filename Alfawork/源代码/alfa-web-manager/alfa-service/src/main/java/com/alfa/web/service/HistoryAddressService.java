package com.alfa.web.service;

import com.alfa.web.pojo.HistoryAddress;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/6/29.
 */
public interface HistoryAddressService {
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
    int insertSelective(HistoryAddress record);

    /**
     * 根据条件查询记录集
     */
    List<HistoryAddress> selectByParams(Criteria example);

    /**
     * 根据主键查询记录
     */
    HistoryAddress selectByPrimaryKey(Long id);
}
