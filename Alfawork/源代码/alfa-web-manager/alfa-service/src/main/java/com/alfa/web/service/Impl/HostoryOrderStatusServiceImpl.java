package com.alfa.web.service.Impl;

import com.alfa.web.dao.order.HostoryOrderStatusMapper;
import com.alfa.web.pojo.HostoryOrderStatus;
import com.alfa.web.service.HostoryOrderStatusService;
import com.alfa.web.util.WebUtil;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19.
 */
@Service
public class HostoryOrderStatusServiceImpl implements HostoryOrderStatusService {

    @Autowired
    private HostoryOrderStatusMapper hostoryOrderStatusMapper;

    private static final Logger logger = LoggerFactory.getLogger(HostoryOrderStatusServiceImpl.class);

    @Override
    public int countByParams(Criteria example) {
        int count=this.hostoryOrderStatusMapper.countByParams(example);
        logger.debug("count:{}",count);
        return count;
    }

    @Override
    public int insertSelective(HostoryOrderStatus record) {
        WebUtil.prepareInsertParams(record);
        return this.hostoryOrderStatusMapper.insertSelective(record);
    }

    @Override
    public List<HostoryOrderStatus> selectByParams(Criteria example) {
        return this.hostoryOrderStatusMapper.selectByParams(example);
    }
}
