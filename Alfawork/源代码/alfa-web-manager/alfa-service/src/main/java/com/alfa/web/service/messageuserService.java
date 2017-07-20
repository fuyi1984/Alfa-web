package com.alfa.web.service;

import com.alfa.web.pojo.publishmessage;

import java.util.List;

/**
 * Created by Administrator on 2017/7/20.
 */
public interface messageuserService {

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(publishmessage record);

    /**
     * 根据Messageid批量删除记录
     * @param list
     * @return
     */
    int batchdeleteByMessageid(List<String> list);
}
