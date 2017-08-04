package com.alfa.web.dao;

import com.alfa.web.pojo.SysUsers;
import com.alfa.web.pojo.userregisterbehavior;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/24.
 */
public interface userregisterbehaviorMapper {

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(userregisterbehavior record);

    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 根据条件查询记录集
     */
    List<userregisterbehavior> selectByParams(Criteria example);

    /**
     * 根据主键批量删除记录
     * @param list
     * @return
     */
    int batchdeleteByPrimaryKey(List<String> list);


    /**
     * 根据条件删除记录
     */
    int deleteByParams(Criteria example);
}
