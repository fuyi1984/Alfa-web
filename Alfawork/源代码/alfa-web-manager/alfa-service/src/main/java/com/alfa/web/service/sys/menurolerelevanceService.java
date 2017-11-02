package com.alfa.web.service.sys;

import com.alfa.web.pojo.OrderComment;
import com.alfa.web.pojo.menurolerelevance;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * 菜单角色多对多关系表
 */
public interface menurolerelevanceService {

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
    int insertSelective(menurolerelevance record);

    /**
     * 根据条件查询记录集
     */
    List<menurolerelevance> selectByParams(Criteria example);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(menurolerelevance record);

    /**
     * 批量插入记录
     * @param recordlst
     * @return
     */
    int Batchinsert(List<menurolerelevance> recordlst);
}
