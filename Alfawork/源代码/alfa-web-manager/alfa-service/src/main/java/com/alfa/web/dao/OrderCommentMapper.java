package com.alfa.web.dao;

import com.alfa.web.pojo.OrderComment;
import com.alfa.web.pojo.Orders;
import com.alfa.web.util.pojo.Criteria;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/11.
 */
public interface OrderCommentMapper {
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
    int deleteByPrimaryKey(Long orderid);

    /**
     * 保存属性不为空的记录
     */
    int insertSelective(OrderComment record);

    /**
     * 批量插入记录
     * @param recordlst
     * @return
     */
    int insertBatch(HashMap<String,Object> map);
    /**
     * 根据条件查询记录集
     */
    List<OrderComment> selectByParams(Criteria example);

}
