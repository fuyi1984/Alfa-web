package com.alfa.web.service.Impl;

import com.alfa.web.dao.sms.orderSmsVwMapper;
import com.alfa.web.pojo.orderSmsVw;
import com.alfa.web.service.orderSmsVwService;
import com.alfa.web.util.pojo.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17.
 */
@Service
public class orderSmsVwServiceImpl implements orderSmsVwService {

    private static final Logger logger = LoggerFactory.getLogger(orderSmsVwServiceImpl.class);

    @Autowired
    private orderSmsVwMapper orderSmsVwMapper;

    @Override
    public List<orderSmsVw> selectByParams(Criteria example) {
        return this.orderSmsVwMapper.selectByParams(example);
    }
}
