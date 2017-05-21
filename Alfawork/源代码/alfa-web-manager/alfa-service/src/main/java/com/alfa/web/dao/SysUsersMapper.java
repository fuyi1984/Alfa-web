package com.alfa.web.dao;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.util.pojo.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/26.
 */
public interface SysUsersMapper {

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
    int deleteByPrimaryKey(Long userSid);

    *//**
     * 保存记录,不管记录里面的属性是否为空
     *//*
    int insert(SysUsers record);

    *//**
     * 保存属性不为空的记录
     *//*
    int insertSelective(SysUsers record);

    *//**
     * 根据条件查询记录集
     *//*
    List<SysUsers> selectByParams(Criteria example);

    *//**
     * 根据主键查询记录
     *//*
    SysUsers selectByPrimaryKey(Long userSid);

    *//**
     * 根据条件更新属性不为空的记录
     *//*
    int updateByParamsSelective(@Param("record") SysUsers record, @Param("condition") Map<String, Object> condition);

    *//**
     * 根据条件更新记录
     *//*
    int updateByParams(@Param("record") SysUsers record, @Param("condition") Map<String, Object> condition);

    *//**
     * 根据主键更新属性不为空的记录
     *//*
    int updateByPrimaryKeySelective(SysUsers record);

    *//**
     * 根据主键更新记录
     *//*
    int updateByPrimaryKey(SysUsers record);*/

}
