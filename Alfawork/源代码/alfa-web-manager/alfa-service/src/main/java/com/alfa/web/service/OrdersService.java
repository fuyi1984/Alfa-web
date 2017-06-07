package com.alfa.web.service;

import com.alfa.web.pojo.Orders;
import com.alfa.web.pojo.SysUsers;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */
public interface OrdersService {

    /**
     * 插入订单信息
     */
    boolean insert(Orders record) throws Exception;

    /**
     * 选择性插入订单信息
     *
     * @param record 订单信息
     * @return 执行条数
     */
    int insertSelective(Orders record);

    /**
     * 根据orderid  查询订单
     * @param orderid
     * @return
     */
    Orders selectByPrimaryKey(Long orderid);

    /**
     * 查询数据总行数
     *
     * @param example 查询条件
     * @return 行数
     */
    int countByParams(Criteria example);

    /**
     * 根据条件查询订单集合
     *
     * @param example 查询条件
     * @return 订单List
     */
    List<Orders> selectByParams(Criteria example);

    /**
     * 根据订单主键删除订单
     *
     * @param userSid 订单主键
     * @return 执行条数
     */
    int deleteByPrimaryKey(Long orderid);

    /**
     * 删除配置
     *
     * @param record
     * @return
     */
    boolean delete(Long orderid);

    /**
     * 根据条件更新订单
     *
     * @param record 订单
     * @return 执行条数
     */
    int updateByPrimaryKeySelective(Orders record);

    /**
     * 根据条件更新订单
     *
     * @param record 用户管理
     * @return 执行条数
     */
    int updateByPrimaryKey(Orders record);
}
