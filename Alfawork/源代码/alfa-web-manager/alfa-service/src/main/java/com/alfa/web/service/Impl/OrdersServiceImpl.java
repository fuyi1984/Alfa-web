package com.alfa.web.service.Impl;

import com.alfa.web.dao.order.OrdersMapper;
import com.alfa.web.pojo.Orders;
import com.alfa.web.service.OrdersService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/6/7.
 */
@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersMapper ordersMapper;

    private static final Logger logger = LoggerFactory.getLogger(OrdersServiceImpl.class);

    @Override
    public boolean insert(Orders record) throws Exception{
        boolean result = false;

        WebUtil.prepareInsertParams(record);
        int levelResult = this.ordersMapper.insertSelective(record);

        if (levelResult == 1) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    @Override
    public int insertSelective(Orders record) {
        return this.ordersMapper.insertSelective(record);
    }

    @Override
    public Orders selectByPrimaryKey(Long orderid) {
        return this.ordersMapper.selectByPrimaryKey(orderid);
    }

    @Override
    public int countByParams(Criteria example) {
        int count=this.ordersMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public List<Orders> selectByParams(Criteria example) {
        List<Orders> orders=this.ordersMapper.selectByParams(example);
        return orders;
    }

    @Override
    public int deleteByPrimaryKey(Long orderid) {
        return this.ordersMapper.deleteByPrimaryKey(orderid);
    }

    @Override
    public int batchdeleteByPrimaryKey(List<String> list) {
        return this.ordersMapper.batchdeleteByPrimaryKey(list);
    }

    @Override
    public boolean delete(Long orderid) {

        boolean result = false;

        Orders order = this.ordersMapper.selectByPrimaryKey(orderid);

        Criteria condition = new Criteria();
        condition.put("orderid", order.getOrderid());

        int configResult = this.ordersMapper.deleteByPrimaryKey(orderid);

        if (configResult == 0) {
            result = false;
        } else {
            result = true;
        }

        return result;
    }

    @Override
    public int updateByPrimaryKeySelective(Orders record) {
        return this.ordersMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Orders record) {
        return this.ordersMapper.updateByPrimaryKey(record);
    }

    @Override
    public int batchupdateorderStatus(List<String> list) {
        return this.ordersMapper.batchupdateorderStatus(list);
    }

    @Override
    public int batchupdateorderWorker(Criteria example) {
        return this.ordersMapper.batchupdateorderWorker(example);
    }

    @Override
    public int batchcompleteorderStatus(List<String> list) {
        return this.ordersMapper.batchcompleteorderStatus(list);
    }

    @Override
    public int updateWorkerIdByParams(Criteria example) {
        return this.ordersMapper.updateWorkerIdByParams(example);
    }

    @Override
    public int batchupdateSmsStatus(Criteria example) {
        return this.ordersMapper.batchupdateSmsStatus(example);
    }
}
