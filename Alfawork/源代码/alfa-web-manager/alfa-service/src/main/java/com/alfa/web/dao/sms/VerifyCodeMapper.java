package com.alfa.web.dao.sms;

import com.alfa.web.pojo.VerifyCode;
import com.alfa.web.util.pojo.Criteria;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/11.
 */
public interface VerifyCodeMapper {
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
    int deleteByPrimaryKey(Long verifySid);

    /**
     * 保存记录,不管记录里面的属性是否为空
     */
    int insert(VerifyCode record);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(VerifyCode record);

    /**
     * 根据条件查询记录集
     */
    List<VerifyCode> selectByParams(Criteria example);

    /**
     * 根据主键查询记录
     */
    VerifyCode selectByPrimaryKey(Long verifySid);

    /**
     * 根据条件更新属性不为空的记录
     */
    int updateByParamsSelective(@Param("record") VerifyCode record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据条件更新记录
     */
    int updateByParams(@Param("record") VerifyCode record, @Param("condition") Map<String, Object> condition);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(VerifyCode record);

    /**
     * 根据主键更新记录
     */
    int updateByPrimaryKey(VerifyCode record);
}
