package com.alfa.web.dao.sys;

import com.alfa.web.pojo.SysAccount;
import com.alfa.web.util.pojo.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15.
 */
public interface SysAccountMapper {
   /* *//**
     * 根据条件查询记录总数
     *//*
    int countByParams(Criteria example);

    *//**
     * 根据条件删除记录
     *//*
    int deleteByParams(Criteria example);

    *//**
     * 根据主键删除记录
     *//*
    int deleteByPrimaryKey(Long accountSid);

    *//**
     * 保存记录,不管记录里面的属性是否为空
     *//*
    int insert(SysAccount record);

    *//**
     * 保存属性不为空的记录
     *//*
    int insertSelective(SysAccount record);

    *//**
     * 根据条件查询记录集
     *//*
    List<SysAccount> selectByParams(Criteria example);

    *//**
     * 根据主键查询记录
     *//*
    SysAccount selectByPrimaryKey(Long accountSid);

    *//**
     * 根据条件更新属性不为空的记录
     *//*
    int updateByParamsSelective(@Param("record") SysAccount record, @Param("condition") Map<String, Object> condition);

    *//**
     * 根据条件更新记录
     *//*
    int updateByParams(@Param("record") SysAccount record, @Param("condition") Map<String, Object> condition);

    *//**
     * 根据主键更新属性不为空的记录
     *//*
    int updateByPrimaryKeySelective(SysAccount record);

    *//**
     * 根据主键更新记录
     *//*
    int updateByPrimaryKey(SysAccount record);


    *//**
     * 根据sids批量更新余额与透支余额
     *//*
    int batchUpdateBalanceOverLimit(SysAccount record);*/
}
