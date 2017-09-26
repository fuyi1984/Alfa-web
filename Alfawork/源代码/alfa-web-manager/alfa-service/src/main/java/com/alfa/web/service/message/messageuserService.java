package com.alfa.web.service.message;

import com.alfa.web.pojo.OrderComment;
import com.alfa.web.pojo.messageuser;
import com.alfa.web.pojo.publishmessage;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface messageuserService {

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(messageuser record);

    /**
     * 根据Messageid批量删除记录
     * @param list
     * @return
     */
    int batchdeleteByMessageid(List<String> list);

    /**
     * 根据主键批量删除记录
     * @param list
     * @return
     */
    int batchdeleteByPrimaryKey(List<String> list);

    /**
     * 根据条件查询记录集
     */
    List<messageuser> selectByParams(Criteria example);

    /**
     * 批量插入记录
     * @param recordlst
     * @return
     */
    int Batchinsert(List<messageuser> recordlst);

    /**
     * 根据主键更新属性不为空的记录
     */
    int updateByParamsSelective(messageuser record);

    /**
     * 根据条件查询记录总数
     */
    int countByParams(Criteria example);
}
