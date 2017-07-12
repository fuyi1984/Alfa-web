package com.alfa.web.service;

import com.alfa.web.pojo.OrderComment;
import com.alfa.web.util.pojo.Criteria;

import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
public interface OrderCommentService {
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
    int Batchinsert(List<OrderComment> recordlst);

    /**
     * 根据条件查询记录集
     */
    List<OrderComment> selectByParams(Criteria example);

    /**
     * 批量删除订单评论
     * @param list
     * @return
     */
    int batchdeleteordercomment(List<String> list);
}

