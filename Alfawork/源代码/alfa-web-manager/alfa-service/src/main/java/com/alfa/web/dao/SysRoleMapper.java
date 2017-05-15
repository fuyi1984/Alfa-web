package com.alfa.web.dao;

import com.alfa.web.pojo.SysRole;
import com.alfa.web.util.pojo.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/24.
 */
public interface SysRoleMapper {
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
    int deleteByPrimaryKey(Long roleSid);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(SysRole record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(SysRole record);

    /**
     * 根据条件查询记录集
     */
    List<SysRole> selectByParams(Criteria example);

    /**
     * 根据主键查询记录
     */
    SysRole selectByPrimaryKey(Long roleSid);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByParamsSelective(@Param("record") SysRole record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByParams(@Param("record") SysRole record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(SysRole record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(SysRole record);
}
