package com.alfa.web.service.Impl;

import com.alfa.web.dao.OrderCommentMapper;
import com.alfa.web.dao.OrdersMapper;
import com.alfa.web.pojo.OrderComment;
import com.alfa.web.service.OrderCommentService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/7/11.
 */
@Service
public class OrderCommentServiceImpl implements OrderCommentService {

    @Autowired
    private OrderCommentMapper orderCommentMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrderCommentServiceImpl.class);

    @Override
    public int countByParams(Criteria example) {
        return 0;
    }

    @Override
    public int deleteByParams(Criteria example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Long orderid) {
        return 0;
    }

    @Override
    public int insertSelective(OrderComment record) {
        WebUtil.prepareInsertParams(record);
        return this.orderCommentMapper.insertSelective(record);
    }

    @Override
    public int Batchinsert(List<OrderComment> recordlst) {

        /*for(OrderComment orderComment:recordlst){
            WebUtil.prepareInsertParams(orderComment);
        }*/

        HashMap<String,Object> map=new HashMap<String, Object>();
        map.put("list",recordlst);
        return this.orderCommentMapper.insertBatch(map);

        //return 0;
    }

    @Override
    public List<OrderComment> selectByParams(Criteria example) {
        return null;
    }
}
