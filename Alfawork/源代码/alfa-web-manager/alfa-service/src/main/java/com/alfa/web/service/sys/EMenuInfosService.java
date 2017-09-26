package com.alfa.web.service.sys;

import com.alfa.web.pojo.EMenuInfos;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/30.
 */
public interface EMenuInfosService {

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
    int insertSelective(EMenuInfos record);

    /**
     * 根据条件查询记录集
     */
    List<EMenuInfos> selectByParams(Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(EMenuInfos record);

    /**
     * 根据主键删除记录
     */
    int deleteByPrimaryKey(Long id);
}
