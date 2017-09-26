package com.alfa.web.dao.message;

import com.alfa.web.pojo.Orders;
import com.alfa.web.pojo.publishmessage;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface publishmessageMapper {

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(publishmessage record);

    /**
     * 根据条件查询记录集
     */
    List<publishmessage> selectByParams(Criteria example);

    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);

    /**
     * 根据主键批量删除记录
     * @param list
     * @return
     */
    int batchdeleteByPrimaryKey(List<String> list);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByPrimaryKeySelective(publishmessage record);

}
