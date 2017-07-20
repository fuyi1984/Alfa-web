package com.alfa.web.dao;

import com.alfa.web.pojo.messageuser;
import com.alfa.web.pojo.publishmessage;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface messageuserMapper {
    /**
     * 保存属性不为空的记录
     */
    int insertSelective(messageuser record);

    /**
     * 根据主键批量删除记录
     * @param list
     * @return
     */
    int batchdeleteByMessageid(List<String> list);

    /**
     * 根据条件查询记录集
     */
    List<messageuser> selectByParams(Criteria example);
}
