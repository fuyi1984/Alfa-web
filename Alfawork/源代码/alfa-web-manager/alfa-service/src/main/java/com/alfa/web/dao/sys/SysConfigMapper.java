package com.alfa.web.dao.sys;

import com.alfa.web.util.pojo.Criteria;
import com.alfa.web.pojo.SysConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/26.
 */
public interface SysConfigMapper {
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
    int deleteByPrimaryKey(Long configSid);

    /**
     * 根据主键批量删除记录
     */
    int batchdeleteByPrimaryKey(List<String> list);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insertSysConfig(SysConfig record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(SysConfig record);

    /**
     * 根据条件查询记录集
     */
    List<SysConfig> selectByParams(Criteria example);

    /**
     * 根据主键查询记录
     */
    SysConfig selectByPrimaryKey(Long configSid);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByParamsSelective(@Param("record") SysConfig record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByParams(@Param("record") SysConfig record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(SysConfig record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(SysConfig record);
}
