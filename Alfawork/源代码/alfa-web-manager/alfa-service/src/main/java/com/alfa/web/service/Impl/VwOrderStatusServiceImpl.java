package com.alfa.web.service.Impl;

import com.alfa.web.dao.order.VwOrderStatusMapper;
import com.alfa.web.pojo.VwOrderStatus;
import com.alfa.web.service.VwOrderStatusService;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/5.
 */
@Service
public class VwOrderStatusServiceImpl implements VwOrderStatusService {

    private static final Logger logger = LoggerFactory.getLogger(VwOrderStatusServiceImpl.class);

    @Autowired
    private VwOrderStatusMapper vwOrderStatusMapper;

    @Override
    public List<VwOrderStatus> selectByParams(Criteria example) {
        return this.vwOrderStatusMapper.selectByParams(example);
    }
}
